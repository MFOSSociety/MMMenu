##############################################################################################
## Simple selenium script to scrape todays menu from the muj menu website made by ~someone~ ##
## - Hardik Srivastava (oddlyspaced)                                                        ##
##############################################################################################
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import json
options = Options()
options.add_argument('--headless')
browser = webdriver.Chrome(options=options)
link = "http://manipaljaipurpro.000webhostapp.com/#menu"
browser.get(link)

# breakfast
breakfast = []
# sample link - /html/body/div[2]/section[2]/div/div/div/div/div[2]/ul/li[1]
counter = 1
while True :
    try :
        element = browser.find_element_by_xpath("/html/body/div[2]/section[2]/div/div/div/div/div[2]/ul/li[%d]"% (counter))
        text = str(element.text).split("\n")[0]
        breakfast.append(text)
        counter = counter + 1
    except :
        break

# lunch
counter = 1
lunch = []
while True :
    try :
        element = browser.find_element_by_xpath("/html/body/div[2]/section[2]/div/div/div/div/div[3]/ul/li[%d]"% (counter))
        text = str(element.text).split("\n")[0]
        lunch.append(text)
        counter = counter + 1
    except :
        break

# Hi tea
counter = 1
hi_tea = []
while True :
    try :
        element = browser.find_element_by_xpath("/html/body/div[2]/section[2]/div/div/div/div/div[4]/ul/li[%d]"% (counter))
        text = str(element.text).split("\n")[0]
        hi_tea.append(text)
        counter = counter + 1
    except :
        break

# Dinner
counter = 1
dinner = []
while True :
    try :
        element = browser.find_element_by_xpath("/html/body/div[2]/section[2]/div/div/div/div/div[5]/ul/li[%d]"% (counter))
        text = str(element.text).split("\n")[0]
        dinner.append(text)
        counter = counter + 1
    except :
        break

# creating single string for all the menu lists
breakfast_string = ""
for item in breakfast :
    breakfast_string = breakfast_string + "#" + item

lunch_string = ""
for item in lunch :
    lunch_string = lunch_string + "#" + item

hitea_string = ""
for item in hi_tea :
    hitea_string = hitea_string + "#" + item

dinner_string = ""
for item in dinner : 
    dinner_string = dinner_string + "#" + item

# {"menu": [{"time": "Breakfast", "food": "xxx"}, {"time": "Lunch", "food": "yyy"}, {"time": "Hi-Tea", "food": "zzz"}, {"time": "Dinner", "food": "zzz"}]}
json_string = "{\"menu\": [{\"time\": \"Breakfast\", \"food\": \"" + breakfast_string + "\"}, {\"time\": \"Lunch\", \"food\": \"" + lunch_string + "\"}, {\"time\": \"Hi-Tea\", \"food\": \"" + hitea_string +  "\"}, {\"time\": \"Dinner\", \"food\": \"" + dinner_string + "\"}]}"
#print(json_string)

f = open("menu.json", "w+")
f.write(json_string)
f.close()