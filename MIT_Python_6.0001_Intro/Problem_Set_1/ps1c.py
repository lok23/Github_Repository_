#ps1c

annual_salary = int(input("Enter your annual salary:"))
total_cost = 1000000
semi_annual_raise = .07
low = 0
high = 10000 #Converted to percentages, high is equal to 100%
steps = 0
month = 0


while True:
    portion_down_payment = 0.25 * total_cost
    current_savings = 0
    monthly_salary = annual_salary / 12
    portion_saved = (low + high) / 2
    percent = (portion_saved / 10000)
    count = 0

    for month in range(0, 36):
        current_savings += current_savings * 0.04 / 12
        current_savings += monthly_salary * percent
        if count == 6:
            monthly_salary += monthly_salary * semi_annual_raise
            count = 0
        count += 1

    #if it is possible to pay the down payment within 3 years, this part will return the best saving rate.
    if abs(current_savings - portion_down_payment) <= 100:
        print('Best saving rate: ', '%.2f' % (portion_saved / 100), '%')
        print('Steps to reach: ', steps)
        print(current_savings)
        break

    #if our calculated saving rate did not get us within 100 dollars of the down payment, we readjust our parameters by reducing "high".
    elif abs(current_savings - portion_down_payment) > 100 and current_savings > portion_down_payment:
        high = portion_saved
        steps += 1

    #if our calculated saving rate did not get us within 100 dollars of the down payment, we readjust our parameters by increasing "low".   
    elif abs(current_savings - portion_down_payment) > 100 and current_savings < portion_down_payment:
        low = portion_saved
        steps += 1

    #this will only occur if saving 100% of income isn't enough to make the down payment; "low" will become 100%, the same as "high".     
    if low == high:
        print('It is not possible to pay the down payment in three years.')
        break
