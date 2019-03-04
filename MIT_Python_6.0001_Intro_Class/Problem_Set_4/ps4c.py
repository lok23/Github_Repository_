# Problem Set 4C
# Name: <your name here>
# Collaborators:
# Time Spent: x:xx

import string
from ps4a import get_permutations

### HELPER CODE ###
def load_words(file_name):
    '''
    file_name (string): the name of the file containing 
    the list of words to load    
    
    Returns: a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    '''
    
    print("Loading word list from file...")
    # inFile: file
    inFile = open(file_name, 'r')
    # wordlist: list of strings
    wordlist = []
    for line in inFile:
        wordlist.extend([word.lower() for word in line.split(' ')])
    print("  ", len(wordlist), "words loaded.")
    return wordlist

def is_word(word_list, word):
    '''
    Determines if word is a valid word, ignoring
    capitalization and punctuation

    word_list (list): list of words in the dictionary.
    word (string): a possible word.
    
    Returns: True if word is in word_list, False otherwise

    Example:
    >>> is_word(word_list, 'bat') returns
    True
    >>> is_word(word_list, 'asdf') returns
    False
    '''
    word = word.lower()
    word = word.strip(" !@#$%^&*()-_+={}[]|\:;'<>?,./\"")
    return word in word_list


### END HELPER CODE ###

WORDLIST_FILENAME = 'words.txt'

# you may find these constants helpful
VOWELS_LOWER = 'aeiou'
VOWELS_UPPER = 'AEIOU'
CONSONANTS_LOWER = 'bcdfghjklmnpqrstvwxyz'
CONSONANTS_UPPER = 'BCDFGHJKLMNPQRSTVWXYZ'

class SubMessage(object):
    def __init__(self, text):
        '''
        Initializes a SubMessage object
                
        text (string): the message's text

        A SubMessage object has two attributes:
            self.message_text (string, determined by input text)
            self.valid_words (list, determined using helper function load_words)
        '''
        self.message_text = text
        self.valid_words = load_words(WORDLIST_FILENAME)
    
    def get_message_text(self):
        '''
        Used to safely access self.message_text outside of the class
        
        Returns: self.message_text
        '''
        return self.message_text

    def get_valid_words(self):
        '''
        Used to safely access a copy of self.valid_words outside of the class.
        This helps you avoid accidentally mutating class attributes.
        
        Returns: a COPY of self.valid_words
        '''
        return self.valid_words
                
    def build_transpose_dict(self, vowels_permutation):
        '''
        vowels_permutation (string): a string containing a permutation of vowels (a, e, i, o, u)
        
        Creates a dictionary that can be used to apply a cipher to a letter.
        The dictionary maps every uppercase and lowercase letter to an
        uppercase and lowercase letter, respectively. Vowels are shuffled 
        according to vowels_permutation. The first letter in vowels_permutation 
        corresponds to a, the second to e, and so on in the order a, e, i, o, u.
        The consonants remain the same. The dictionary should have 52 
        keys of all the uppercase letters and all the lowercase letters.

        Example: When input "eaiuo":
        Mapping is a->e, e->a, i->i, o->u, u->o
        and "Hello World!" maps to "Hallu Wurld!"

        Returns: a dictionary mapping a letter (string) to 
                 another letter (string). 
        '''
        
        punctuation = list(" !@#$%^&*()-_+={}[]|\:;'<>?,./\"")
        my_dict = {}

        #everything in my_dict is mapped to itself except for vowels,
        #which are mapped to their corresponding place in the input
        
        for counter, letter in enumerate(vowels_permutation.lower()):
            my_dict[VOWELS_LOWER[counter]] = letter
            
        for counter, letter in enumerate(vowels_permutation.upper()):
            my_dict[VOWELS_UPPER[counter]] = letter
            
        for letter in CONSONANTS_LOWER:
            my_dict[letter] = letter
            
        for letter in CONSONANTS_UPPER:
            my_dict[letter] = letter
            
        for character in punctuation:
            my_dict[character] = character
            
        return my_dict
    
    def apply_transpose(self, transpose_dict):
        '''
        transpose_dict (dict): a transpose dictionary
        
        Returns: an encrypted version of the message text, based 
        on the dictionary
        '''
        
        shifted_message_string = []
        for letter in self.message_text:
            shifted_message_string.append(transpose_dict[letter])
        return ''.join(shifted_message_string)
        
class EncryptedSubMessage(SubMessage):
    def __init__(self, text):
        '''
        Initializes an EncryptedSubMessage object

        text (string): the encrypted message text

        An EncryptedSubMessage object inherits from SubMessage and has two attributes:
            self.message_text (string, determined by input text)
            self.valid_words (list, determined using helper function load_words)
        '''
        SubMessage.__init__(self, text)

    def decrypt_message(self):
        '''
        Attempt to decrypt the encrypted message 
        
        Idea is to go through each permutation of the vowels and test it
        on the encrypted message. For each permutation, check how many
        words in the decrypted text are valid English words, and return
        the decrypted message with the most English words.
        
        If no good permutations are found (i.e. no permutations result in 
        at least 1 valid word), return the original string. If there are
        multiple permutations that yield the maximum number of words, return any
        one of them.

        Returns: the best decrypted message    
        
        Hint: use your function from Part 4A
        '''
        
        word_list = self.get_valid_words()
        transpose_dict_list = []
        found_real_word = []
        real_word_counter = []
        decrypted_string_storage = []
        best_matches = []
        permutation_list = get_permutations("aeiou") #creates a list of all possible 
        count = 0 #count has same role as in ps4b    #permutations of aeiou
        for permutation in permutation_list:
            #creates a list of all possible dict with permutations of its vowels
            transpose_dict_list.append(self.build_transpose_dict(permutation))  

        for s in transpose_dict_list:
            #applying each dict to self via apply_transpose
            decrypted_string = self.apply_transpose(s)
            decrypted_string_list = decrypted_string.split()
            decrypted_string_storage.append(decrypted_string) #the "correct" answers is in
            for word in decrypted_string_list:                #decrypted_string_storage,
                if is_word(word_list, word):                  #along with many wrong answers
                    found_real_word.append(word)
            found_real_word.append("NewCipher") #Once NewCipher separates each shifted copy
        for word in found_real_word:
            if word != "NewCipher":
                count += 1   #count keeps track of number of real words in each shifted copy
            else:
                real_word_counter.append(count)
                count = 0
        best_match = max(real_word_counter)
        for s, value in enumerate(real_word_counter):
            if value == best_match:
                #the place where value is highest is the same as the place where the
                #"correct" answers are in decrypted_string_storage
                best_matches.append(decrypted_string_storage[s])
        return ', '.join(list(set(best_matches))) #set removes all duplicates
            

        

if __name__ == '__main__':

    # Example test case
    message = SubMessage("Hello World!")
    permutation = "eaiuo"
    enc_dict = message.build_transpose_dict(permutation)
    print("Original message:", message.get_message_text(), "Permutation:", permutation)
    print("Expected encryption:", "Hallu Wurld!")
    print("Actual encryption:", message.apply_transpose(enc_dict))
    enc_message = EncryptedSubMessage(message.apply_transpose(enc_dict))
    print("Decrypted message:", enc_message.decrypt_message())
     
    #TODO: WRITE YOUR TEST CASES HERE

    message = SubMessage('Hairy Chest')
    permutation = 'iauoe'
    enc_dict = message.build_transpose_dict(permutation)
    print("Original message:", message.get_message_text(), "Permutation:", permutation)
    print("Expected encryption:", "w!")
    print("Actual encryption:", message.apply_transpose(enc_dict))
    enc_message = EncryptedSubMessage(message.apply_transpose((enc_dict)))
    print('Decrypted message:', enc_message.decrypt_message())
