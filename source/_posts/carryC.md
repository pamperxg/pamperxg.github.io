---
title: carryC
date: 2019-07-07 22:48:03
tags: [notes,...]
---

```python
import sys
import os
import re

print(sys.argv[0])
print(os.getcwd())
print(os.path.abspath('.'))
print(os.path.abspath('..'))

def findtxt(path, ret):
    """Finding the *.txt file in specify path"""
    filelist = os.listdir(path)
    for filename in filelist:
        de_path = os.path.join(path, filename)
        if os.path.isfile(de_path):
            if de_path.endswith(".txt"): #Specify to find the txt file.
                ret.append(de_path)
        else:
            findtxt(de_path, ret)
            

txt_files = []
findtxt(root_path,txt_files)

def get_item(files_list):
    res = []
    for i in range(len(files_list)):
        file_items = []
        with open(files_list[i]) as f:
        #     print(f.read().split('\n'))
        #     print(type(f.read().split('\n')))
            file_items = f.read().split('\n')
            file_highlights = []
            for j in range(len(file_items)):
                file_highlights.append(file_items[j].split('.')[1])
            res.append(file_highlights)
    print(res)
    return res

res_func_list = get_item(txt_files)
```

