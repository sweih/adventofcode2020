import collections
import math
import re
import sys

f = open("day19-part2.input", "r")
lines = [l.rstrip('\n') for l in f.read().split("\n\n")]

rules = lines[0]
data = lines[1]


rules = {}
for rule in rules0.split('\n'):
    tmp = rule.split(': ')
    rules[tmp[0]] = tmp[1]

#print(rules)

def checkRules1(lineNumber):
    rule = rules[lineNumber]
    #print(rule)

    if re.fullmatch(r'"."', rule):
        return rule[1]
    else:
        rules2 = rule.split(' | ')
        recursionArray = []
        for rule2 in rules2:
            linenos = rule2.split(' ')
            tmp = []
            for num in linenos:
                tmp.append(checkRules2(num))
            recursionArray.append(''.join(tmp))
        return '(' + '|'.join(recursionArray) + ')'


def checkRules2(lineNumber):
    rule = rules[lineNumber]
    #print(rule)
    if (lineNumber=='8'):
      return checkRules2('42') + '+'

    if (lineNumber=='11'):
        #11: 42 31 | 42 11 31
        r42 = checkRules2('42')
        r31 = checkRules2('31')
        resultArray = []
        for n in range(1,50):
            resultArray.append('(' + r42 + '){' + str(n)  + '}' + '(' + r31 + '){' + str(n) + '}' )
        return '(' + '|'.join(resultArray) + ')'


    if re.fullmatch(r'"."', rule):
        return rule[1]
    else:
        rules2 = rule.split(' | ')
        recursionArray = []
        for rule2 in rules2:
            linenos = rule2.split(' ')
            tmp = []
            for num in linenos:
                tmp.append(checkRules2(num))
            recursionArray.append(''.join(tmp))
        return '(' + '|'.join(recursionArray) + ')'


#print("Part 1: ");
#matchCode = checkRules1('0')
#result = 0
#for line in data.split('\n'):
#    if (re.fullmatch(matchCode,line)):
#        result+=1
#print(result)

print("Part 2: ");
matchCode = checkRules2('0')
result = 0
for line in data.split('\n'):
    if (re.fullmatch(matchCode,line)):
        result+=1
print(result)