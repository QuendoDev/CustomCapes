public class CapeUtils
{

public static void downloadCape(final AbstractClientPlayer player)
    { 

String username = player.getNameClear();

        if (username != null && !username.isEmpty())
        {
          if(CapeController.<name> ==true) {
        	  final ResourceLocation <name> = new ResourceLocation("<the location of the folder you created>(in my case)kinoclient/cosmetics/capes/2012.png");
        	  if(<name>!=null) {
        		  player.setLocationOfCape(<name>);
        	  }
          }
         }
          else {
        	  String ofCapeUrl = "http://s.optifine.net/capes/" + username + ".png";
              String mptHash = FilenameUtils.getBaseName(ofCapeUrl);
              final ResourceLocation rl = new ResourceLocation("capeof/" + mptHash);
              TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
              ITextureObject tex = textureManager.getTexture(rl);

              if (tex != null && tex instanceof ThreadDownloadImageData)
              {
                  ThreadDownloadImageData thePlayer = (ThreadDownloadImageData)tex;

                  if (thePlayer.imageFound != null)
                  {
                      if (thePlayer.imageFound.booleanValue())
                      {
                          player.setLocationOfCape(rl);
                      }

                      return;
                  }
              }

              IImageBuffer iib = new IImageBuffer()
              {
                  ImageBufferDownload ibd = new ImageBufferDownload();
                  public BufferedImage parseUserSkin(BufferedImage var1)
                  {
                      return CapeUtils.parseCape(var1);
                  }
                  public void func_152634_a()
                  {
                      player.setLocationOfCape(rl);
                  }
              };
              ThreadDownloadImageData textureCape = new ThreadDownloadImageData((File)null, ofCapeUrl, (ResourceLocation)null, iib);
              textureCape.pipeline = true;
              textureManager.loadTexture(rl, textureCape);
          }
        }
}
public static BufferedImage parseCape(BufferedImage img)
    {
        int imageWidth = 64;
        int imageHeight = 32;
        int srcWidth = img.getWidth();

        for (int srcHeight = img.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; imageHeight *= 2)
        {
            imageWidth *= 2;
        }

        BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage(img, 0, 0, (ImageObserver)null);
        g.dispose();
        return imgNew;
    }
}
