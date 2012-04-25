package babylon;
//  Babylon Chat
//  Copyright (C) 1997-2002 J. Andrew McLaughlin
// 
//  This program is free software; you can redistribute it and/or modify it
//  under the terms of the GNU General Public License as published by the Free
//  Software Foundation; either version 2 of the License, or (at your option)
//  any later version.
// 
//  This program is distributed in the hope that it will be useful, but
//  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
//  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
//  for more details.
//  
//  You should have received a copy of the GNU General Public License along
//  with this program; if not, write to the Free Software Foundation, Inc.,
//  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
//
//  babylonBmpIO.java
//

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import babylon.*;

public class babylonBmpIO
{
    // Our own private bitmap library.  This code was adapted from C
    // code belonging to my Visopsys operating system kernel, with help
    // from this JavaWorld article:
    // http://www.javaworld.com/javaworld/javatips/jw-javatip60.html

    public static short BMP_BPP_MONO  = 1;
    public static short BMP_BPP_16    = 4;
    public static short BMP_BPP_256   = 8;
    public static short BMP_BPP_16BIT = 16;
    public static short BMP_BPP_24BIT = 24;
    public static short BMP_BPP_32BIT = 32;

    public static int BMP_COMP_NONE = 0;
    public static int BMP_COMP_RLE4 = 1;
    public static int BMP_COMP_RLE8 = 2;
    public static int BMP_COMP_BITF = 3;

    public static int BMP_HEADER_SIZE = 54;


    public babylonBmpIO()
    {
    }

    public void writeBmp(Image image, int width, int height, String filename)
	throws Exception
    {
	File canvasFile = null;
	FileOutputStream fileStream = null;
	
	int[] bitmapData = new int[width * height];
	    
	// Turn the image into an array of pixel data
	PixelGrabber grabber =
	    new PixelGrabber(image, 0, 0, width, height, bitmapData, 0,
			     width);

	grabber.grabPixels();

	// Okee dokee.  The image data is now an array of ints.  We can
	// write the file
	canvasFile = new File(filename);
	fileStream = new FileOutputStream(canvasFile);

	//fileStream.write(bytes);
	
	bitmapHeader header = new bitmapHeader();
	int padBytes = 0;
	int dataSize = 0;

	// Do we need to pad each line of the image with extra bytes?
	// The file data needs to be on doubleword boundaries.
	if (((width * 3) % 4) != 0)
	    padBytes = 4 - ((width * 3) % 4);

	byte[] pad = new byte[padBytes];

	// The data size is number of lines, times line width + pad bytes
	dataSize = ((width * 3) + padBytes) * height;

	// Create the file header
	header.size = BMP_HEADER_SIZE + dataSize;
	header.dataStart = BMP_HEADER_SIZE;
	header.headerSize = 40;
	header.width = width;
	header.height = height;
	header.planes = 1;
	header.bitsPerPixel = BMP_BPP_24BIT;
	header.compression = BMP_COMP_NONE;
	header.dataSize = dataSize;

	// Write the header
	fileStream.write(header.toByteArray());

	// Now loop through the image data.
	for (int rowCounter = height - 1; rowCounter >= 0; rowCounter-- )
	    {
		for (int pixelCounter = 0; pixelCounter < width;
		     pixelCounter ++)
		    {
			// Get the color value
			int colorValue = bitmapData[(rowCounter * width) +
						   pixelCounter];
		
			byte[] rgb = new byte[3];
			rgb[0] = (byte) (colorValue & 0xFF);
			rgb[1] = (byte) ((colorValue >> 8) & 0xFF);
			rgb[2] = (byte) ((colorValue >> 16) & 0xFF);
			fileStream.write(rgb);
		    }
		fileStream.write(pad);
	    }

	fileStream.close();
    }


    class bitmapHeader
    {
	// The .bmp header

	public int size = 0;
	public int reserved = 0;
	public int dataStart = 0;
	public int headerSize = 0;
	public int width = 0;
	public int height = 0;
	public short planes = 0;
	public short bitsPerPixel = 0;
	public int compression = 0;
	public int dataSize = 0;
	public int hResolution = 0;
	public int vResolution = 0;
	public int colors = 0;
	public int importantColors = 0;

	public bitmapHeader()
	{
	}

	public byte[] toByteArray()
	{
	    // Returns the header as an array of bytes
	    byte[] array = new byte[BMP_HEADER_SIZE];
	    int count = 0;

	    array[count++] = 'B';
	    array[count++] = 'M';

	    intBytes(size, array, count);  count += 4;
	    intBytes(reserved, array, count);  count += 4;
	    intBytes(dataStart, array, count);  count += 4;
	    intBytes(headerSize, array, count);  count += 4;
	    intBytes(width, array, count);  count += 4;
	    intBytes(height, array, count);  count += 4;
	    shortBytes(planes, array, count);  count += 2;
	    shortBytes(bitsPerPixel, array, count);  count += 2;
	    intBytes(compression, array, count);  count += 4;
	    intBytes(dataSize, array, count);  count += 4;
	    intBytes(hResolution, array, count);  count += 4;
	    intBytes(vResolution, array, count);  count += 4;
	    intBytes(colors, array, count);  count += 4;
	    intBytes(importantColors, array, count);

	    return(array);
	}

	private void intBytes(int value, byte[] array, int offset)
	{
	    array[offset++] = (byte) (value & 0xFF); 
	    array[offset++] = (byte) ((value >> 8) & 0xFF); 
	    array[offset++] = (byte) ((value >> 16) & 0xFF); 
	    array[offset] = (byte) ((value >> 24) & 0xFF); 
	}

	private void shortBytes(short value, byte[] array, int offset)
	{
	    array[offset++] = (byte) (value & 0xFF); 
	    array[offset++] = (byte) ((value >> 8) & 0xFF); 
	}
    }
}
