###########################
# 6.0002 Problem Set 1a: Space Cows 
# Name:
# Collaborators:
# Time:

from ps1_partition import get_partitions
import time

#================================
# Part A: Transporting Space Cows
#================================

# Problem 1
def load_cows(filename):
    """
    Read the contents of the given file.  Assumes the file contents contain
    data in the form of comma-separated cow name, weight pairs, and return a
    dictionary containing cow names as keys and corresponding weights as values.

    Parameters:
    filename - the name of the data file as a string

    Returns:
    a dictionary of cow name (string), weight (int) pairs
    """
    # TODO: Your code here
    f = open("ps1_cow_data.txt", 'r')

    contents = f.read()

    cows = {}

    contents = contents.replace(" ", "!@#")
    contents = contents.split("\n")
    contents = " ".join(contents)
    contents = contents.replace(" ", ",")
    contents = contents.replace("!@#", " ")
    contents = contents.split(",")
    for i in range(0, len(contents)//2):
        cows[contents[2*i]] = int(contents[(2*i) + 1])
    return cows


# Problem 2
def greedy_cow_transport(cows,limit=10):
    """
    Uses a greedy heuristic to determine an allocation of cows that attempts to
    minimize the number of spaceship trips needed to transport all the cows. The
    returned allocation of cows may or may not be optimal.
    The greedy heuristic should follow the following method:
    1. As long as the current trip can fit another cow, add the largest cow that will fit
        to the trip
    2. Once the trip is full, begin a new trip to transport the remaining cows
    Does not mutate the given dictionary of cows.
    Parameters:
    cows - a dictionary of name (string), weight (int) pairs
    limit - weight limit of the spaceship (an int)
    
    Returns:
    A list of lists, with each inner list containing the names of cows
    transported on a particular trip and the overall list containing all the
    trips
    """
    # TODO: Your code here
    cows_copy = sorted(cows, key=cows.get, reverse=True) 
    result = []
    while len(cows_copy) > 0:
        totalWeight = 0
        trip = []
        for name in cows_copy[:]:
            if cows[name] + totalWeight <= limit:
                trip.append(name)
                totalWeight = totalWeight + cows[name]
                cows_copy.remove(name)
        result.append(trip)
    return result


# Problem 3
def brute_force_cow_transport(cows,limit=10):
    """
    Finds the allocation of cows that minimizes the number of spaceship trips
    via brute force.  The brute force algorithm should follow the following method:
    1. Enumerate all possible ways that the cows can be divided into separate trips 
        Use the given get_partitions function in ps1_partition.py to help you!
    2. Select the allocation that minimizes the number of trips without making any trip
        that does not obey the weight limitation
            
    Does not mutate the given dictionary of cows.
    Parameters:
    cows - a dictionary of name (string), weight (int) pairs
    limit - weight limit of the spaceship (an int)
    
    Returns:
    A list of lists, with each inner list containing the names of cows
    transported on a particular trip and the overall list containing all the
    trips
    """
    result = []
    for partition in get_partitions(cows.keys()):
        totalWeight_list = []
        for trip in partition:
            totalWeight = 0
            for name in trip:
                totalWeight = totalWeight + cows[name]
            if totalWeight > limit:
                totalWeight_list.append("fail")
            totalWeight_list.append(totalWeight)
        if "fail" not in totalWeight_list:
            result.append(partition)
    return result


# Problem 4
def compare_cow_transport_algorithms():
    """
    Using the data from ps1_cow_data.txt and the specified weight limit, run your
    greedy_cow_transport and brute_force_cow_transport functions here. Use the
    default weight limits of 10 for both greedy_cow_transport and
    brute_force_cow_transport.
    
    Print out the number of trips returned by each method, and how long each
    method takes to run in seconds.
    Returns:
    Does not return anything.
    """
    # TODO: Your code here
    start = time.time()
    end = time.time()
    print("Time to calculate greedy algorithm: ",  end-start, " seconds")
    print("Number of trips: ", len(greedy_cow_transport(load_cows("ps1_cow_data.txt"))))
    print()
    
    start = time.time()
    length = []
    cows_list = brute_force_cow_transport(load_cows("ps1_cow_data.txt"))
    for i in cows_list:
        score = 0
        for trip in i:
            score += 1
        length.append(score)
    minimum = min(length)
    end = time.time()
    print('Time to calculate brute force algorithm: ', end-start, " seconds")
    print('Number of trips: ', minimum)
    

compare_cow_transport_algorithms()
