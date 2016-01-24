package business;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils
{
   /**
    * Helper method, just calls <tt>readFully(in, b, 0, b.length)</tt>
    */
   public static int readFully(InputStream in, byte[] b)
           throws IOException
   {
      return readFully(in, b, 0, b.length);
   }

   /**
    * Same as the normal <tt>in.read(b, off, len)</tt>, but tries to ensure that
    * <p/>
    * the entire len number of bytes is read.
    * <p/>
    * <p/>
    * <p/>
    * If the end of file is reached before any bytes are read, returns -1.
    * <p/>
    * Otherwise, returns the number of bytes read.
    */
   public static int readFully(InputStream in, byte[] b, int off, int len)
           throws IOException
   {
      int total = 0;
      for (; ;)
      {
         int got = in.read(b, off + total, len - total);
         if (got < 0)
         {
            return (total == 0) ? -1 : total;
         }
         else
         {
            total += got;
            if (total == len)
               return total;
         }
      }
   }

   /** Copy the content of the given input stream to a byte array.
    */
   public static byte[] toByteArray(InputStream is)
           throws IOException
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
      byte[] bytes = new byte[1024];
      int read = is.read(bytes);
      while (read > 0)
      {
         baos.write(bytes, 0, read);
         read = is.read(bytes);
      }
      bytes = baos.toByteArray();
      return bytes;
   }
}