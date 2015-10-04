Findings by Xuan Fan 653226
The skeleton of the whole stream sipher is quite good.
We definitly needs some skills from Part A1, although I have done A1 in Python but this time I challenge Java.
The whole progress can be stated as below
--------------------------------------------
| Stream cipher                            |
--------------------------------------------
- Given: Share key, prime

1. Get Parity Word checksum (will be used in reset register)
    - Key is given
    - Implement XOR function by shifting key by 64 bit each time
2. Derive a support key
    - key and prime p is given
    - Compute the key % p to get result
3. Most significant byte/bits - MSB
    - Used to get MSB for further compute
    - Value and number of bits are given
    - if is less than 2^8 - 1 which 255 then return value directly
    - otherwise shift it to right until there are only n bits left ( leftmost digits)
    - return result in bytes
4. _crypt, can be encrypt or decpryt, E(bi) = bi ⊕ MSB[(ari−1 + b) mod p] => E(bi) = bi ⊕ MSB(r_i)
    - Register is to point out which index we are working on
    - reset () is to set r_i to r0 which is Parity Word checksum
    - UpdateShiftRegister :  ri = (ari−1 + b) mod p
        - updates the shift register from ri−1 to ri.
    - input is a byte array then I identify its bitlength to set up a result array which will be the same length as input msg
    - Create a for loop to implement XOR with MSB of r_i in each cell
        - every cell done then update its register shift to next
    - return a result in byte[]
5. decrpyt/encrypt call the same _crypt function
    - There are no difference between de/encrypt, _crypt just need to shift value to the right place

--------------------------------------------
| DES                                      |
--------------------------------------------
Done some research on that, but the skeletons provided by teachers is in python.
Not quite familiar with bytes operation in python and I need to transform skeletons to Java first then start to work.
I did not actually make it out but I think my skeleton is right
The permutation is very complex to do some actions because the to move the IP must use a high efficient loop but i have run out of time.
The constant declarations are many, so I decide to put it into an individual file
Steps as below:
1. Input 64 bits plain text and subsitute into IP ( initial permutation)
2. After modify IP, plain text will be divide to Left and Right with 32 bits each (L0, R0)
3. We use keys (16 sub keys) to control 16 rounds calculations with F function(fiestel)
4. After 16 rounds, exchange L and R then join together
5. Inverse IP (or final permutation)
6. Output 64 bits cipher
