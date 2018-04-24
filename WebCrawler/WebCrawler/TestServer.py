import requests
import threading

def test():
    s = "6a4gasd4g6a4"
    x = 0
    while True:
        try:
            x+=1
            r = requests.post('http://59.110.163.18:8080/IAE/student/index.sycu',data = s)
            print(x)
        except:
            continue


threads = []
t1 = threading.Thread(target=test)
threads.append(t1)
t2 = threading.Thread(target=test)
threads.append(t2)
t3 = threading.Thread(target=test)
threads.append(t3)
t4 = threading.Thread(target=test)
threads.append(t4)

for t in threads:
    t.setDaemon(True)
    t.start()

t.join()
