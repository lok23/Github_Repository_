# Problem Set 2, hangman.py
# Name: 
# Collaborators:
# Time spent:

# Hangman Game
# -----------------------------------
# Helper code
# You don't need to understand this helper code,
# but you will have to know how to use the functions
# (so be sure to read the docstrings!)
import random
import string

WORDLIST_FILENAME = "words.txt"


def load_words():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = line.split()
    print("  ", len(wordlist), "words loaded.")
    return wordlist



def choose_word(wordlist):
    """
    wordlist (list): list of words (strings)
    
    Returns a word from wordlist at random
    """
    return random.choice(wordlist)

# end of helper code

# -----------------------------------

# Load the list of words into the variable wordlist
# so that it can be accessed from anywhere in the program
wordlist = load_words()


def is_word_guessed(secret_word, letters_guessed):
    '''
    secret_word: string, the word the user is guessing; assumes all letters are
      lowercase
    letters_guessed: list (of letters), which letters have been guessed so far;
      assumes that all letters are lowercase
    returns: boolean, True if all the letters of secret_word are in letters_guessed;
      False otherwise
    '''

    word_guessed_yes = True
    for letter in secret_word:
        if letter not in letters_guessed:
            word_guessed_yes = False
    return word_guessed_yes



def get_guessed_word(secret_word, letters_guessed):
    '''
    secret_word: string, the word the user is guessing
    letters_guessed: list (of letters), which letters have been guessed so far
    returns: string, comprised of letters, underscores (_), and spaces that represents
      which letters in secret_word have been guessed so far.
    '''

    displayed_list = []
    for letter in secret_word:
        if letter in letters_guessed:
            displayed_list.append(letter)
        else:
            displayed_list.append("_")
    return ' '.join(displayed_list)



def get_available_letters(letters_guessed):
    '''
    letters_guessed: list (of letters), which letters have been guessed so far
    returns: string (of letters), comprised of letters that represents which letters have not
      yet been guessed.
    '''

    displayed_list = []
    alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    for letters in alphabet:
        if letters not in letters_guessed:
           displayed_list.append(letters)
    return ''.join(displayed_list)


    
def get_unique_letters(secret_word):
    '''
    This is a custom helper function for def hangman(secret_word) that
    counts the unique letters in secret_word in order to calculate final score.
    For example, if two or more of the same letters are in a word, only one of
    #that letter counts.
    '''
    
    unique_letters = 0
    test_list = []
    for letter in secret_word:
        if letter not in test_list:
            test_list.append(letter)
            unique_letters += 1
    return unique_letters



def hangman(secret_word):
    '''
    secret_word: string, the secret word to guess.
    
    Starts up an interactive game of Hangman.
    
    * At the start of the game, let the user know how many 
      letters the secret_word contains and how many guesses s/he starts with.
      
    * The user should start with 6 guesses

    * Before each round, you should display to the user how many guesses
      s/he has left and the letters that the user has not yet guessed.
    
    * Ask the user to supply one guess per round. Remember to make
      sure that the user puts in a letter!
    
    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computer's word.

    * After each guess, you should display to the user the 
      partially guessed word so far.
    
    Follows the other limitations detailed in the problem write-up.
    '''
    # FILL IN YOUR CODE HERE AND DELETE "pass"
    letters_guessed = []
    count = 0
    guess_number = 6 #we get six guesses
    warnings = 3 #we get three warnings
    for letter in secret_word: 
        count += 1
    print("Welcome to the game Hangman!") 
    print("The secret word has " + str(count) + " letters.")
    while True:
        print("-------------")
        print("You have " + str(guess_number) + " guesses left.")
        print("Available letters: " + (get_available_letters(letters_guessed)))
        guess = input("Please guess a letter:")
        guess = guess.lower()
        if guess in secret_word:
            letters_guessed.append(guess) 
            print("Good guess: " + (get_guessed_word(secret_word, letters_guessed)))  #return the skeleton of the word after each guess
        
        else:
            
            if not guess.isalpha(): #if the user did not enter a letter of alphabet,
                warnings -= 1       #subtract a warning. If warnings <= 0, subtract
                if warnings > 0:    #a guess.
                    print("Oops! That is not a valid letter. "\
                + "You have " + str(warnings) + " warnings left: "\
                + (get_guessed_word(secret_word, letters_guessed))) #return the skeleton of the word after each guess
                if warnings <= 0:
                    print("Oops! That is not a valid letter. "\
                + "You have no warnings left, so you lose one guess: "\
                + (get_guessed_word(secret_word, letters_guessed))) 
                guess_number -= 1
                
            else:
                
                if guess in letters_guessed: #if the user guessed a letter in 
                    warnings -= 1            #letters guessed, subtract a warning.
                    if warnings > 0:         #If warnings <= 0, subtract a guess.
                        print("Oops! You've already guessed that letter. "\
                + "You have " + str(warnings) + " warnings left: "\
                + (get_guessed_word(secret_word, letters_guessed)))
                    if warnings <= 0:
                        print("Oops! You've already guessed that letter. "\
                + "You have no warnings left, so you lose one guess: "\
                + (get_guessed_word(secret_word, letters_guessed)))
                        guess_number -= 1

                #if the guess is wrong and a vowel, subtract 2 guesses.        
                if guess not in letters_guessed and guess in "aeiou":
                    letters_guessed.append(guess)
                    print("Oops! That letter is not in my word: "\
                    + (get_guessed_word(secret_word, letters_guessed)))
                    guess_number -= 2

                #if the guess is wrong and a consonant, subtract 2 guesses.    
                if guess not in letters_guessed and guess not in "aeiou": 
                    letters_guessed.append(guess)
                    print("Oops! That letter is not in my word: "\
                    + (get_guessed_word(secret_word, letters_guessed)))
                    guess_number -= 1

            #if out of guesses, display game over screen        
            if guess_number == 0:
                print("Sorry, you ran out of guesses. The word was "\
                + secret_word +".")
                break
            
        #if all letter of secret_word is in letters_guessed, you win.
        if is_word_guessed(secret_word, letters_guessed):
            print("Congratulations, you won! ")
            final_score = guess_number * get_unique_letters(secret_word)
            print("Your total score for this game is: " + str(final_score))
            break


# When you've completed your hangman function, scroll down to the bottom
# of the file and uncomment the first two lines to test
#(hint: you might want to pick your own
# secret_word while you're doing your own testing)


# -----------------------------------


def match_with_gaps(my_word, other_word):
    '''
    my_word: string with _ characters, current guess of secret word
    other_word: string, regular English word
    returns: boolean, True if all the actual letters of my_word match the 
        corresponding letters of other_word, or the letter is the special symbol
        _ , and my_word and other_word are of the same length;
        False otherwise: 
    '''
       
    empty_list = []
    empty_list2 = []
    count = 0
    my_new_word = my_word.replace(" ", "")
    if len(my_new_word) != len(other_word):
        return False
    for letter in my_new_word:
        empty_list.append(letter)
    for letter in other_word:
        empty_list2.append(letter)
    while True:
        if count >= len(other_word):
            return True
        for letter in range(0, len(other_word)):
            if empty_list[letter] == "_" and empty_list2[letter] in empty_list:
                return False
            if empty_list[count] == empty_list2[count] or empty_list[count] == "_":
                count += 1
            else:
                return False



def show_possible_matches(my_word):
    '''
    my_word: string with _ characters, current guess of secret word
    returns: nothing, but should print out every word in wordlist that matches my_word
             Keep in mind that in hangman when a letter is guessed, all the positions
             at which that letter occurs in the secret word are revealed.
             Therefore, the hidden letter(_ ) cannot be one of the letters in the word
             that has already been revealed.

    '''
    
    possible_matches = []
    for word in wordlist:
        if match_with_gaps(my_word, word):
            possible_matches.append(word)
    if not possible_matches:
        print('No matches found')
    else:
        for word in possible_matches:
            print(word, end=' ')




def hangman_with_hints(secret_word):
    '''
    secret_word: string, the secret word to guess.
    
    Starts up an interactive game of Hangman.
    
    * At the start of the game, let the user know how many 
      letters the secret_word contains and how many guesses s/he starts with.
      
    * The user should start with 6 guesses
    
    * Before each round, you should display to the user how many guesses
      s/he has left and the letters that the user has not yet guessed.
    
    * Ask the user to supply one guess per round. Make sure to check that the user guesses a letter
      
    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computer's word.

    * After each guess, you should display to the user the 
      partially guessed word so far.
      
    * If the guess is the symbol *, print out all words in wordlist that
      matches the current guessed word. 
    
    Follows the other limitations detailed in the problem write-up.
    '''
    
    letters_guessed = []
    count = 0
    guess_number = 6
    warnings = 3
    for letter in secret_word: 
        count += 1
    print("Welcome to the game Hangman!") 
    print("The secret word has " + str(count) + " letters.")
    while True:
        print("-------------")
        print("You have " + str(guess_number) + " guesses left.")
        print("Available letters: " + (get_available_letters(letters_guessed)))
        guess = input("Please guess a letter:")
        guess = guess.lower()

        #The only new feature, presents possible answers based on letters_guessed.
        if guess == '*':  
            print("Possible word matches are:")
            show_possible_matches(get_guessed_word(secret_word, letters_guessed))

        if guess in letters_guessed:
            warnings -= 1
            if warnings > 0:
                print("Oops! You've already guessed that letter. "\
                + "You have " + str(warnings) + " warnings left: "\
                + (get_guessed_word(secret_word, letters_guessed)))
            if warnings <= 0:
                print("Oops! You've already guessed that letter. "\
                + "You have no warnings left, so you lose one guess: "\
                + (get_guessed_word(secret_word, letters_guessed)))
                guess_number -= 1

        #In hangman_with_hints, we have to make sure not to punish players for guessing "*"               
        if guess in secret_word and guess not in letters_guessed and guess != "*":
            letters_guessed.append(guess)
            print("Good guess: " + (get_guessed_word(secret_word, letters_guessed)))
    
        else:

            #In hangman_with_hints, we have to make sure not to punish players for guessing "*"
            if not guess.isalpha() and guess != '*' :
                warnings -= 1
                if warnings > 0:
                    print("Oops! That is not a valid letter. "\
                + "You have " + str(warnings) + " warnings left: "\
                + (get_guessed_word(secret_word, letters_guessed)))
                if warnings <= 0:
                    print("Oops! That is not a valid letter. "\
                + "You have no warnings left, so you lose one guess: "\
                + (get_guessed_word(secret_word, letters_guessed)))
                    guess_number -= 1
                
            else:

                #In hangman_with_hints, we have to make sure not to punish players for guessing "*"        
                if guess not in letters_guessed and guess in "aeiou" and guess != "*":
                    letters_guessed.append(guess)
                    print("Oops! That letter is not in my word: "\
                    + (get_guessed_word(secret_word, letters_guessed)))
                    guess_number -= 2

                #In hangman_with_hints, we have to make sure not to punish players for guessing "*"    
                if guess not in letters_guessed and guess not in "aeiou" and guess != "*":
                    
                    letters_guessed.append(guess)
                    print("Oops! That letter is not in my word: "\
                    + (get_guessed_word(secret_word, letters_guessed)))
                    guess_number -= 1
                    
            if guess_number <= 0:
                print("Sorry, you ran out of guesses. The word was "\
                + secret_word +".")
                break

        if is_word_guessed(secret_word, letters_guessed):
            print("Congratulations, you won! ")
            final_score = guess_number * get_unique_letters(secret_word)
            print("Your total score for this game is: " + str(final_score))
            break

# When you've completed your hangman_with_hint function, comment the two similar
# lines above that were used to run the hangman function, and then uncomment
# these two lines and run this file to test!
# Hint: You might want to pick your own secret_word while you're testing.


if __name__ == "__main__":
    # pass

    # To test part 2, comment out the pass line above and
    # uncomment the following two lines.

    # secret_word = choose_word(wordlist)
    # hangman(secret_word)

    ###############

    # To test part 3 re-comment out the above lines and
    # uncomment the following two lines.

    secret_word = choose_word(wordlist)
    hangman_with_hints(secret_word)

