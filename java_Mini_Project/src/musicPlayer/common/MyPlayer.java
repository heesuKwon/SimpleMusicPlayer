package musicPlayer.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

//이쪽은 다 퍼온거라서 사실 이해는 안가는데 원래  new InputStream(path)를 받는 player가 path를 받는 MyPlayer로 됨
public class MyPlayer {
   static class Sample{
      private short[] buffer;
      private int size;
      
      
      public Sample(short[] buf, int s) {
         buffer = buf.clone();
         size = s;
      }
      public short[] GetBuffer() {
         return buffer;
      }
      public int GetSize() {
         return size;
      }
   }
   
   public static final int BUFFER_SIZE = 44100000;
   
   private Decoder decoder;
   private AudioDevice out;
   private ArrayList<Sample> samples;
   private int size;
   
   public MyPlayer(String path) {
      Open(path);
   }
   public boolean IsInvalid() {
      return(decoder == null || out == null || samples == null || !out.isOpen());
   }
   protected boolean GetSamples(String path) {
      if(IsInvalid())
         return false;
      try {
         Header header;
         SampleBuffer sb;
         FileInputStream in = new FileInputStream(path);
         Bitstream bitstream = new Bitstream(in);
         if((header = bitstream.readFrame()) == null)
            return false;
         while(size < BUFFER_SIZE && header != null) {
            sb = (SampleBuffer)decoder.decodeFrame(header, bitstream);
            samples.add(new Sample(sb.getBuffer(), sb.getBufferLength()));
            size++;
            bitstream.closeFrame();
            header = bitstream.readFrame();
         }
      }catch(FileNotFoundException e) {
         return false;
      }catch(BitstreamException e) {
         return false;
      }catch(DecoderException e) {
         return false;
      }
      return true;
   }
   
   public boolean Open(String path) {
      Close();
      try{
         decoder = new Decoder();
         out = FactoryRegistry.systemRegistry().createAudioDevice();
         samples = new ArrayList<Sample>(BUFFER_SIZE);
         size = 0;
         out.open(decoder);
         GetSamples(path);
      }catch(JavaLayerException e) {
         decoder = null;
         out = null;
         samples = null;
         return false;
      }
      
      return true;
   }
   public void Play() {
      if(IsInvalid())
         return;
      try {
         for(int i=0; i<size; i++) {
            out.write(samples.get(i).GetBuffer(), 0, samples.get(i).GetSize());
         }
         out.flush();
      }catch(JavaLayerException e) {
         
      }
   }
   public void Close() {
      if((out != null) && !out.isOpen())
         out.close();
      size = 0;
      samples = null;
      out = null;
      decoder = null;
   }
   
   

}