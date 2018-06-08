def f_1(): #через селф передается ссыль на объект этого класса 
    dictionary = {} #map
    input_str = "a b c a b c"
    for i in "!\"'()*,-./:;?@[\\]_{|}~":#clear from symbols
        input_str = input_str.replace(i,'')
    words_mass = [x for x in input_str.split(' ') if x]#разб и изб от пробелов
    
    for i in range (2, len(words_mass) + 1): 
        k = 0
        while (k + i + 1) <= len(words_mass):
            glue = ' '.join(words_mass[k:k+i+1])
            p = 0#wtf
            count = 0
            while input_str.find(glue, p) != -1: #найдено что-то 
                count += 1
                p = input_str.find(glue, p) + len(glue)
            if count > 1:
                dictionary[glue] = count
            k += 1
    print(str(dictionary))

f_1()