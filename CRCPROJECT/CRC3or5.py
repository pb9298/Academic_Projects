# Project : CRC Generation and Error Detection

# Project Part 1 : CRC append bits (N) = 2 and BER (x) = 3
# Project Part 2 : CRC append bits (N) = 4 and BER (x) = 2


import numpy as np
from random import *


# Random pattern generation function for Project Part 1
def randompattern(n):
    global pattern
    global possible_patterns
    if n == '2':
        possible_patterns = ['100', '101', '110', '111']
        x = sample(possible_patterns, 1)  # Pick a random pattern from the list
        pattern = x[0]
        return pattern
    elif n == '4':
        possible_patterns = ['10000', '10001', '10010', '10011',
                             '10100', '10101', '10110', '10111',
                             '11000', '11001', '11010', '11011',
                             '11100', '11101', '11110', '11111']
        x = sample(possible_patterns, 1)  # Pick a random pattern from the list
        pattern = x[0]
        return pattern
    else:
        return ''


# CRC_generator function
def CRC_generator(n, x):
    global i
    i = 0

    # Random 8 bits 128 frames are generated and saved in array_frames
    array_frames = np.array(np.random.random_integers(0, 1, 128 * 8)).reshape(128, 8)

    # Transmitted frames after appending CRC bits will be saved in this empty array
    global transmitted_frames_copy
    transmitted_frames_copy = np.empty((128, 8 + int(n)), dtype=str)

    global transmitted_frames_compare
    transmitted_frames_compare = np.empty((128, 8 + int(n)), dtype=str)

    # All 128 frames will be passed on to a function which will
    # append the CRC bits at the end of each frame.

    # randompattern function is called which will generate
    # 3 bits pattern in order to append 2 bits of CRC
    pattern = randompattern(n)
    if pattern == '':
        print('The number of CRC bits specified are not as per the project description')
        print('Try entering value N = 2 or N = 4')
        exit()
    else:
        while i <= 127:

            # Each frame out of 128 frames is copied as per the loop count and passed on to
            # the CRC_encoder function along with the pattern
            frame = array_frames[i]
            CRC_encoder(frame, pattern)
            i += 1
            if i > 128:
                break

    if x == '3':
        error_addition()

    elif x == '2':
        e = 0
        while e < 9:
            error_addition()
            e += 1
            if e > 9:
                break

    else:
        print('The BER is not specified as per the project description')
        print('Try entering value x = 3 or x = 2')
        exit()


    CRC_syndromedetector()


# CRC_encoder function will append the CRC bits to the
# 128 generated frames

def CRC_encoder(frame, pattern):


    length_pattern = len(pattern)

    # Appends Pattern - 1 (N = 2) zeroes at end of frame
    append_zeros = '0' * (length_pattern - 1)
    append_zeros = list(append_zeros)

    # appended_frame will have padding of Pattern - 1 zeros at
    # the end of the frame
    appended_frame = np.append(frame, append_zeros)

    # Now we have the proper divisor with Pattern - 1 zeros at
    # the end of the frame we can now perform Modulo 2 division
    # by calling modulo2_division and get the remainder of the division
    # which are CRC bits which we need to append
    remainder = modulo2_division(appended_frame, pattern)
    remainder = list(remainder)

    # Appending remainder (CRC bits) in the original frame
    # Now each frame will be of total 10 bits
    transmitted_frame = np.append(frame, remainder)
    transmitted_frames_compare[i] = transmitted_frame
    transmitted_frames_copy[i] = transmitted_frame

    # print(remainder)
    # print "Frame :", str(i)
    # print(transmitted_frame)
    # print('\n')


# Function which will perform modulo 2 divsion on
# appended frame with zero bits and pattern

def modulo2_division(appended_frame, pattern):

    # copying the appended frame into dividend variable
    # and pattern in divisor variable
    dividend = appended_frame
    divisor = pattern

    # Total number of pattern bits to be XORed (Modulo 2 division) with dividend
    numberofbits = len(divisor)

    # Slicing the dividend as per the length of the divisor
    # at **start** to perform XOR (Modulo 2 division) then we will increase the count
    # of numberofbits to include the next bits that will be done in
    # while loop and it will be stored in the sliced_dividend variable except the last bit
    # which we need to handle out separately
    sliced_dividend = dividend[0: numberofbits]

    # Comparison of each bit of dividend and divisor
    while numberofbits < len(dividend):

        if sliced_dividend[0] == '1':

            # As the LSB of the dividend is '1' we can carry out the modulo division with the divisor
            # replace the dividend by the XOR result
            # After performing the XOR operation we will get one bit less than the
            # length of dividend and divisor and it is stored in xor_result
            xor_result = xor(divisor, sliced_dividend)

            # In order to continue performing the modulo 2 division
            # we will pull in the next bit from the dividend
            # and concatenate to the XOR result giving us bits as per the length of the divisor
            sliced_dividend = xor_result + dividend[numberofbits]

        else:

            # If lSB of the dividend is '0' we can not use the divisor we will use the
            # '0' bit string whose length is equal to the length of divisor
            zerodivisor = '0' * numberofbits
            xor_result = xor(zerodivisor, sliced_dividend)

            # In order to continue performing the modulo 2 division
            # we will pull in the next bit from the dividend
            # and concatenate to the XOR result giving us bits as per the length of the divisor
            sliced_dividend = xor_result + dividend[numberofbits]

        # increment numberofbits to move the division further
        numberofbits += 1

    # For the last bit, we have to carry it out
    # normally as increased value of pick will cause
    # Index Out of Bounds.
    if sliced_dividend[0] == '1':
        sliced_dividend = xor(divisor, sliced_dividend)
    else:
        zerodivisor = '0' * numberofbits
        sliced_dividend = xor(zerodivisor, sliced_dividend)

    remainder = sliced_dividend
    return remainder


# Function performs XOR operation and return the result
# and it will be 2 bit as the 1st bit is eliminated by our choice of divisor
# with respect to dividend

def xor(x, y):

    # initialize result to store the bit values after comparison
    result = []

    # Traverse all bits, if bits are
    # same, then XOR is 0, else 1
    for row in range(1, len(y)):
        if x[row] == y[row]:
            result.append('0')
        else:
            result.append('1')

    return ''.join(result)



# Function for adding error with BER = 1/1000 (x = 3)

def error_addition():

    # Pick a random frame between 0 and 127
    random_frame_number = randint(0, 127)
    # print random_frame_number

    # Copying the entire frame then will select a bit to introduce the error
    error_frame = transmitted_frames_copy[random_frame_number]
    # print "Frame : " + str(random_frame_number) + ' Without error\n'
    # print(transmitted_frames_copy[random_frame_number])

    # Pick a random bit from randomly selected frame between 0 to 9
    random_frame_bit_number = randint(0, 9)

    # print(random_frame_bit_number)
    error_frame_bit = error_frame[random_frame_bit_number]

    # print(type(error_frame_bit))
    # print(error_frame_bit)
    # This check will see the bit value and accordingly will flip it to introduce error

    if error_frame_bit == '1':
        error_frame[random_frame_bit_number] = '0'
        transmitted_frames_copy[random_frame_number] = error_frame
        # print(error_frame)
    else:
        error_frame[random_frame_bit_number] = '1'
        transmitted_frames_copy[random_frame_number] = error_frame
        # print(error_frame)

    # print "\nFrame : " + str(random_frame_number) + ' With error\n'
    # print(transmitted_frames_copy[random_frame_number])


# ----------------------------------------------------------------------------------------------------------------------

# function CRC receiver

def CRC_syndromedetector():
    global i
    i = 0
    while i <= 127:
        received_frame = transmitted_frames_copy[i]

        CRC_decoder(received_frame, pattern)
        i += 1
        if i > 128:
            break


# function for checking the remainder of the received frames by performing
# modulo 2 division with the same pattern

def CRC_decoder(received_frame, pattern):
    remainder = modulo2_division(received_frame, pattern)
    # print(remainder)

    if remainder == '00' or remainder == '0000':
        print "\nTransmitted frame - " + str(i) + " :", transmitted_frames_compare[i]
        print "Received frame - " + str(i) + "    :", transmitted_frames_copy[i]
        print "No error found in frame : " + str(i)
    else:
        print "\n----------------------------------------------------------------------------------------"
        print "\nTransmitted frame - " + str(i) + " :", transmitted_frames_compare[i]
        print "Received frame - " + str(i) + "    :", transmitted_frames_copy[i]
        print "error found in frame : " + str(i)
        print "The remainder received after performing modulo 2 division with the same pattern :", remainder
        print "\n----------------------------------------------------------------------------------------"

# n = '2'
n = str(input('Enter the number of CRC bits to be appended: '))
# x = '3'
x = str(input('Enter the value of x for Bit Error Rate (BER = 1/10^x): '))
CRC_generator(n, x)