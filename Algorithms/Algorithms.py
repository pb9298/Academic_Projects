# Name: Pratik Ramesh Barhate
# Student Id: 1001649826
# Project 01
################################################## Mergesort ###########################################################
import time
import matplotlib as mpl
import numpy as np
mpl.use('TkAgg')
import matplotlib.pyplot as plt


def merge(left, right):
    result = []
    i, j = 0, 0
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result = result + left[i:]
    result = result + right[j:]
    return result


def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    mid = len(arr) // 2
    return merge(merge_sort(arr[:mid]), merge_sort(arr[mid:]))


def plot_merge_sort():
    # randomly generates list of different sizes and call merge_sort function
    inputs = list()
    times = list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        merge_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by Merge sort in time " + str(end - start)
        inputs.append(len(input_lists))
        times.append(end - start)

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs, times, label='Merge sort')
    plt.grid()
    plt.legend()
    plt.show()


#################################################### Heapsort ##########################################################


def heapify(unsorted_array, i, heap_size):
    # max = i
    left_i = 2 * i + 1
    right_i = 2 * i + 2
    if left_i < heap_size and unsorted_array[left_i] > unsorted_array[i]:
        max = left_i
    else:
        max = i

    if right_i < heap_size and unsorted_array[right_i] > unsorted_array[max]:
        max = right_i

    if max != i:
        unsorted_array[max], unsorted_array[i] = unsorted_array[i], unsorted_array[max]
        heapify(unsorted_array, max, heap_size)


def heap_sort(unsorted_array):
    n = len(unsorted_array)
    for i in range(n // 2 - 1, -1, -1):
        heapify(unsorted_array, i, n)
    for i in range(n - 1, 0, -1):
        unsorted_array[0], unsorted_array[i] = unsorted_array[i], unsorted_array[0]
        heapify(unsorted_array, 0, i)
    return unsorted_array


def plot_heap_sort():
    # randomly generates list of different sizes and call heap_sort function
    inputs = list()
    times = list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        heap_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by Heap sort in time " + str(end - start)
        inputs.append(len(input_lists))
        times.append(end - start)

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs, times, label='Heap sort')
    plt.grid()
    plt.legend()
    plt.show()


############################################ Quicksort (Using median of 3) #############################################


def quick_sort(arr):
    left = []
    right = []
    pivot_list = []

    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[0]
        for i in arr:
            if i < pivot:
                left.append(i)
            elif i > pivot:
                right.append(i)
            else:
                pivot_list.append(i)
        left = quick_sort(left)
        right = quick_sort(right)
        return left + pivot_list + right


def plot_quick_sort():
    # randomly generates list of different sizes and call quick_sort function
    inputs = list()
    times = list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        quick_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by quick sort (Median 3) in time " + str(end - start)
        inputs.append(len(input_lists))
        times.append(end - start)

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs, times, label='Quick sort (Median 3')
    plt.grid()
    plt.legend()
    plt.show()


################################################## Insertion sort ######################################################


def insertion_sort(arr):
    for i in range(1, len(arr)):
        key = arr[i]
        j = i - 1
        while j >= 0 and key < arr[j]:
            arr[j + 1] = arr[j]
            j = j - 1
        arr[j + 1] = key

    return arr


def plot_insertion_sort():
    # randomly generates list of different sizes and call insertion_sort function
    inputs = list()
    times = list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        insertion_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by insertion sort in time " + str(end - start)
        inputs.append(len(input_lists))
        times.append(end - start)

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs, times, label='insertion sort')
    plt.grid()
    plt.legend()
    plt.show()


#################################################### Bubble sort #######################################################


def bubble_sort(arr):
    length = len(arr) - 1
    sorted_arr = False
    while not sorted_arr:
        sorted_arr = True
        for i in range(length):
            if arr[i] > arr[i + 1]:
                sorted_arr = False
                arr[i], arr[i + 1] = arr[i + 1], arr[i]
    return arr


def plot_bubble_sort():
    # randomly generates list of different sizes and call bubble_sort function
    inputs = list()
    times = list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        bubble_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by bubble sort in time " + str(end - start)
        inputs.append(len(input_lists))
        times.append(end - start)

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs, times, label='bubble sort')
    plt.grid()
    plt.legend()
    plt.show()


#################################################### All graphs plot ###################################################


def plot_all_graphs():
    # randomly generates list of different sizes and call all functions
    inputs1, inputs2, inputs3, inputs4, inputs5 = list(), list(), list(), list(), list()
    times1, times2, times3, times4, times5 = list(), list(), list(), list(), list()
    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        merge_sort(input_lists)
        end = time.clock()
        print str(len(input_lists)) + " input list numbers got sorted by merge sort in time " + str(end - start)
        inputs1.append(len(input_lists))
        times1.append(end - start)

    print("\n")

    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        heap_sort(input_lists)
        end = time.clock()
        print str(len(input_lists)) + " input list numbers got sorted by heap sort in time " + str(end - start)
        inputs2.append(len(input_lists))
        times2.append(end - start)

    print("\n")

    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        quick_sort(input_lists)
        end = time.clock()
        print str(len(input_lists)) + " input list numbers got sorted by quick sort (Median 3) in time " + str(end - start)
        inputs3.append(len(input_lists))
        times3.append(end - start)

    print("\n")

    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        insertion_sort(input_lists)
        end = time.clock()
        print str(len(input_lists)) + " input list numbers got sorted by insertion sort in time " + str(end - start)
        inputs4.append(len(input_lists))
        times4.append(end - start)

    print("\n")

    for i in range(1, 10):
        # generate some random integers
        input_lists = np.random.randint(0, 100 * i, 100 * i)
        start = time.clock()
        bubble_sort(input_lists)
        end = time.clock()

        print str(len(input_lists)) + " input list numbers got sorted by bubble sort in time " + str(end - start)
        inputs5.append(len(input_lists))
        times5.append(end - start)

    print("\n")

    plt.xlabel('Input Size (numbers in array)')
    plt.ylabel('Time Complexity (seconds)')
    plt.plot(inputs1, times1, label='Merge sort')
    plt.plot(inputs2, times2, label='Heap sort')
    plt.plot(inputs3, times3, label='Quick sort( Median 3 )')
    plt.plot(inputs4, times4, label='Insertion sort')
    plt.plot(inputs5, times5, label='Bubble sort')
    plt.grid()
    plt.legend()
    plt.show()


############################################### Calling Algorithms #####################################################


print("The sorting Algorithms are as follows:\n")
print("1. Mergesort\n2. Heapsort\n3. Quicksort (Using median of 3)\n4. Insertion sort\n5. Bubble sort\n")

user_input = raw_input("Enter numbers separated by a comma:\n").split(",")
input_unsorted_array = [int(item) for item in user_input]

print("\nThe sorted numbers by Mergesort are as follows:")
print(str(merge_sort(input_unsorted_array)) + "\n")

print("The sorted numbers by Heapsort are as follows:")
print(str(heap_sort(input_unsorted_array)) + "\n")

print("The sorted numbers by Quicksort (Using median of 3) are as follows:")
print(str(quick_sort(input_unsorted_array)) + "\n")

print("The sorted numbers by Insertion sort are as follows:")
print(str(insertion_sort(input_unsorted_array)) + "\n")

print("The sorted numbers by Bubble sort are as follows:")
print(str(bubble_sort(input_unsorted_array)) + "\n")

############################################# Calling all graph plots ##################################################

print("The input size vs running time graph is plotted for all algorithms\n")
plot_all_graphs()

############################################## Calling individual graph plots ##########################################

print("The graph plot of a specific sorting algorithm is as follows:")
print("The input size vs running time graph for Merge sort.\n")
plot_merge_sort()

print("\nThe graph plot of a specific sorting algorithm is as follows:")
print("The input size vs running time graph for Heap sort.\n")
plot_heap_sort()

print("\nThe graph plot of a specific sorting algorithm is as follows:")
print("The input size vs running time graph for Quick sort( Median 3 ).\n")
plot_quick_sort()

print("\nThe graph plot of a specific sorting algorithm is as follows:")
print("The input size vs running time graph for Insertion sort.\n")
plot_insertion_sort()

print("\nThe graph plot of a specific sorting algorithm is as follows:")
print("The input size vs running time graph for Bubble sort.\n")
plot_bubble_sort()