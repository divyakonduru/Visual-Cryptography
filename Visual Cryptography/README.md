#Visual Cryptography Project

![Visual Cryptography](title.gif "Visual Cryptography")

Copyright (c) 2015 Justin Blackmon and Joel Bush

This project is licensed under the MIT License

For licensing information refer to LICENSE.md

Justin Blackmon contact: blackmon.justin@gmail.com, 

Joel Bush contact: bush2@pdx.edu

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt 

images, or text. We chose to work with one-time pad encryption, but there are several types of encryption 

available for this kind of process. 

The process we performed has several steps:

1. Convert the source image to pure black and white, no gray-scale.

2. Generate a random key of black and white pixels, of exactly the same size as the original image.

3. Based on the pixels in the original and key images, create a cipher image(details in the following 

links).

4. Expand the cipher and key images to 4 times the size(each pixel becomes a 2x2 grid of pixels).

5. Compare each 2x2 grid of pixels in the expanded key and expanded cipher images, based on the comparison, 

create either a black or white pixel in the decrypted image(details in following links).

6. The resulting decrypted image should be an exact copy of the original black and white image.

Below are several links to sites for more details about the visual cryptography process.

http://users.telenet.be/d.rijmenants/en/visualcrypto.htm

http://www.datagenetics.com/blog/november32013/

 This project can be built with maven following the instructions below:
