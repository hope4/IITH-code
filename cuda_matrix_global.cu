/*
 * Alexandre Maros - 2016
 *
 * Cuda Matrix Multiplication with Global Memory.
 *
 * nvcc cuda_matrix_global.cu -o cg.o
 *
 * Implemented by Alexandre Maros for learning purposes.
 * A version of this code using Shared Memory is in here:
 * https://github.com/alepmaros/cuda_matrix_multiplication
 *
 * Distributed under the MIT Lincese.
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>

//32x32
#define NTHREADS_X 16
#define NTHREADS_Y 32
#define THREADS_PER_BLOCK NTHREADS_X * NTHREADS_Y

/* A macro used for error checking in CUDA function calls
 * Credit to: http://stackoverflow.com/a/14038590 for the gpuErrchk macro.
 */
#define gpuErrchk(ans) { gpuAssert((ans), __FILE__, __LINE__); }
inline void gpuAssert(cudaError_t code, const char *file, int line, bool abort=true)
{
   if (code != cudaSuccess)
   {
      fprintf(stderr,"GPUassert: %s %s %d\n", cudaGetErrorString(code), file, line);
      if (abort) exit(code);
   }
}

__global__ void matrix_mul(int *a, int *b, int *c, int a_ncolumns, int c_nlines, int c_ncolumns)
{

    int column = blockIdx.x * blockDim.x + threadIdx.x;
    int line =  blockIdx.y * blockDim.y + threadIdx.y;

    if (column  >= c_ncolumns || line >= c_nlines)
        return;

    int i, sum = 0;


    int beginA = a_ncolumns * line;
    int beginB = column;

    for (i = 0; i < a_ncolumns; i++)
    {
        sum += a[beginA + i] * b[i * c_ncolumns + beginB];
    }

    c[line * c_ncolumns + column] = sum;
}

int main(){

int d[9] = {363,605,847,507,845,1183,675,1125,1575};
int i, j;
int h1 = 512;
int l;
//int h2 = 256;
//int h3 = 128;
for(l=0;l<9;l++){
  printf("Now we are in value %d\n", l);
    int *x, *m1, *a;
    int *d_x, *d_m1, *d_a;
    int x_nlines, x_ncolumns;
    int m1_nlines, m1_ncolumns;
    int a_nlines, a_ncolumns;

    size_t x_size, m1_size, a_size;

    cudaEvent_t start, stop;
    gpuErrchk( cudaEventCreate(&start) );
    gpuErrchk( cudaEventCreate(&stop) );



    x_nlines = 1;
    x_ncolumns = d[l];

    m1_nlines = d[l];
    m1_ncolumns = h1;

    a_nlines = x_nlines;
    a_ncolumns = m1_ncolumns;


//    printf("a_nlines: %d\na_ncolumns: %d\nb_nlines: %d\nb_ncolumns: %d\nc_nlines: %d\nc_ncolumns: %d\n", a_nlines, a_ncolumns, b_nlines, b_ncolumns, c_nlines, c_ncolumns);
//#endif

   /* if ( a_ncolumns != b_nlines )
    {
        printf("Number of columns in Matrix A should be equals to number of lines in Matrix B\n");
        return EXIT_FAILURE;
    }*/

    x_size = x_nlines * x_ncolumns * sizeof(int);
    m1_size = m1_nlines * m1_ncolumns * sizeof(int);
    a_size = a_nlines * a_ncolumns * sizeof(int);
    gpuErrchk( cudaMalloc((void **) &d_x, x_size) );
    gpuErrchk( cudaMalloc((void **) &d_m1, m1_size) );
    gpuErrchk( cudaMalloc((void **) &d_a, a_size) );

    x = (int *)malloc(x_size);
    m1 = (int *)malloc(m1_size);
    a = (int *)malloc(a_size);

    srand(time(0));
    memset(a, 0, a_nlines*a_ncolumns*sizeof(int));
//    printf("Enter values for A\n");
    for (i = 0; i < x_nlines; i++)
    {
        for (j = 0; j < x_ncolumns; j++)
        {
            x[i * x_ncolumns + j]=rand() % 2;
//	    printf("%d ",a[i* x_ncolumns + j]);
        }
//        printf("\n");
    }

//    printf("Enter values for B\n");
    for (i = 0; i < m1_nlines; i++)
    {
        for (j = 0; j < m1_ncolumns; j++)
        {
            m1[i * m1_ncolumns + j]=rand()%2;
//            printf("%d ",b[i* m1_ncolumns + j]);
        }
//	printf("\n");
    }
//    printf("\n");

    gpuErrchk( cudaMemcpy(d_x, x, x_size, cudaMemcpyHostToDevice) );
    gpuErrchk( cudaMemcpy(d_m1, m1, m1_size, cudaMemcpyHostToDevice) );

    dim3 tbloco = dim3(
                    (int) std::ceil( (double) a_ncolumns / NTHREADS_X ),
                    (int) std::ceil ( (double) a_nlines / NTHREADS_Y ),
                    1
                );

    dim3 tthreads = dim3(
                        NTHREADS_X,
                        NTHREADS_Y,
                        1
                    );

#ifdef __DEBUG
    printf("tbloco.x: %d tbloco.y: %d tbloco.z: %d\n", tbloco.x, tbloco.y, tbloco.z);
    printf("tthreads.x: %d tthreads.y: %d\n", tthreads.x, tthreads.y);
#endif

    cudaEventRecord(start);

    // kernel call
    matrix_mul<<<tbloco,tthreads>>>(d_x, d_m1, d_a, x_ncolumns, a_nlines, a_ncolumns);
    gpuErrchk( cudaPeekAtLastError() );
    gpuErrchk( cudaEventRecord(stop) );
    gpuErrchk( cudaMemcpy(a, d_a, a_size, cudaMemcpyDeviceToHost) );
    gpuErrchk( cudaEventSynchronize(stop) );

    // print Matrix
    for (i = 0; i < a_nlines; i++)
    {
        for (j = 0; j < a_ncolumns; j++)
        {
            printf("%d ", a[i * a_ncolumns + j]);
        }
        printf("\n");
    }
    printf("\n");



        float milliseconds = 0;
        gpuErrchk( cudaEventElapsedTime(&milliseconds, start, stop) );
        printf("The total time taken in milliseconds is :%.5f\n", milliseconds);


    free(x); free(m1); free(a);

    gpuErrchk( cudaFree(d_x) );
    gpuErrchk( cudaFree(d_m1) );
    gpuErrchk( cudaFree(d_a) );

}

return 0;
}
