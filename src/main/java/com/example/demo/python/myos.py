import os

#遍历路径下的文件
def gci(path):
    files = os.listdir(path)
    for fi in files:
        fi_d = os.path.join(path,fi)
        if os.path.isdir(fi_d):
            gci(fi_d)
        else:
            print(fi_d)