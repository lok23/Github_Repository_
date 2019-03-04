# Problem Set 4A
# Name: <your name here>
# Collaborators:
# Time Spent: x:xx

def get_permutations(sequence):
    '''
    Enumerate all permutations of a given string

    sequence (string): an arbitrary string to permute. Assume that it is a
    non-empty string.  

    You MUST use recursion for this part. Non-recursive solutions will not be
    accepted.

    Returns: a list of all permutations of sequence

    Example:
    >>> get_permutations('abc')
    ['abc', 'acb', 'bac', 'bca', 'cab', 'cba']

    Note: depending on your implementation, you may return the permutations in
    a different order than what is listed here.
    '''

    # answer copied from https://repl.it/@pulchrit/Python-Create-string-permutations-recursively
    # Define the base case. If the lengths of the string/sequence is 1 or less,
    # then return that string as a list.
    # Recursion will take the string/sequence all the way down to the base case 
    # and then return the permutations from each recursive call back up the 
    # recursive chain building the permutations by adding in the first letter
    # to all index positions as it does so. 
    if len(sequence) <= 1:
        return list(sequence) 
    
    # Define the recursive case.  
    else:
        # Create an empty list to gather all of the permutations. 
        permutations_final = []
        
        # Remove first letter to variable.
        first_char = sequence[0] 
        
        # Loop through elements created through a recursive call of the function.
        for element in get_permutations(sequence[1:]):
        
            # Convert each element into a list to use .insert method with first character
            element_list = list(element)
            
            # Loop through each index in the length of the element list + 1.
            # Plus one enables us to add the first character onto the very end of the string.
            for i in range(len(element_list) + 1):  
                
                # Create a copy of element_list to modify while keeping the original
                # element_list intact for the loop to iterate over. 
                element_list_copy = element_list[:]
                
                # Insert the first character into each index position of the element_list_copy.
                # This will happen in each recursive call, building up the permutations
                # as the recursive calls are returned. 
                element_list_copy.insert(i, first_char)
                
                # Join each element_list_copy into a string and append it to the permutations list.
                permutations_final.append("".join(element_list_copy))
        
        # Return the permutations list. This will return every permutation list from each 
        # recursive call to the preceeding recursive call, thus building up the 
        # permutations for all of the recursive calls. And it will ultimately return
        # the complete list of permutations for the sequence.
        return permutations_final

if __name__ == '__main__':
#    #EXAMPLE
#    example_input = 'abc'
#    print('Input:', example_input)
#    print('Expected Output:', ['abc', 'acb', 'bac', 'bca', 'cab', 'cba'])
#    print('Actual Output:', get_permutations(example_input))
    
#    # Put three example test cases here (for your sanity, limit your inputs
#    to be three characters or fewer as you will have n! permutations for a 
#    sequence of length n)

    pass #delete this line and replace with your code here

