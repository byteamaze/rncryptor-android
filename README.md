# RNCryptor-Android
Android Performance-optimized implementation of Rob Napier's RNCryptor

Extension of JNCryptor (Java port for RNCryptor), just replace pbkdf2 password generation with jni.

That's the core problem why JNCryptor is too slow.

#### Q: Why not use RNCryptorNative?
A: It only support base64 string encrypt, we need byte array encrypt.

# Using RNCryptor-Android

#### Import

Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```groovy
	dependencies {
	        implementation 'com.github.ByteAmaze:RNCryptor-Android:1.0'
	}
```

#### Usage

```JAVA
JNCryptor cryptor = new AES256JNCryptor();
byte[] plaintext = "Hello, World!".getBytes();
String password = "secretsquirrel";

try {
  byte[] ciphertext = cryptor.encryptData(plaintext, password.toCharArray());
} catch (CryptorException e) {
  // Something went wrong
  e.printStackTrace();
}
```
