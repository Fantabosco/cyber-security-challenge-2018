#!/usr/bin/python2.7

import hashlib, base64, sys


def decriptMe():
    with open("TOP_secret.zip.enc") as f:
        return f.read()

def encryptionKey(k): 
    m = hashlib.md5()
    m.update(k)
    key = m.hexdigest()
    return key

def decryption(key, cyphertext):
    plaintext = ""
    k = 0
    for i in base64.b64decode(cyphertext):
        p = ord(key[k]) ^ ord(i)
        plaintext = plaintext + chr(p)
        key += chr(p)
        k += 1
    return plaintext

def encryption(plaintext):
    key = encryptionKey("key")
    print "[*] Key: {}\n[*] Plaintext: {}".format(key, str(len(plaintext)))
    cyphertext = ""
    for i in xrange(len(plaintext)):
        c = (ord(key[i]) ^ ord(plaintext[i]))
        cyphertext = cyphertext + chr(c)
        key += plaintext[i]
    return base64.b64encode(cyphertext)

def check(plaintext):
    if plaintext[-65:] == "6c81d06ac6d2709a81f76a9bf6c3f5933002f00053302447b122260a0ac0c18e\n":
        return True
    return False

def main(key):
    enkey = encryptionKey(key)
    print "[*] Key Encrypted: %s" % (enkey)
    plaintext = decryption(enkey, decriptMe())
    if check(plaintext):
        print "[*] Key Correct! Plaintext:\n %s" % (plaintext)
    
if __name__=="__main__":
    if len(sys.argv) != 2:
        print 'Give me the key\n Example: %s key' % (sys.argv[0])
        exit(1)
    main(sys.argv[1])

