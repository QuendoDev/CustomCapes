1. Install MCP (the tutorial is going to be for MCP 910
2. Install optifine for that MCP version
3. Run the client and check it doesn't have bugs
4. Create a folder in the package "assets.minecraft"
5. Put your cape texture (with the format that is on this repository) in that folder
6. Create a class (mine is going to be "CapeController" and do something like this:
  
  public static boolean <name> = true;
  
7. Open the class optifine.CapeUtils
8. Go to the method "downloadCape(final AbstractClientPlayer player)" and copy this:
  
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
9. Run the client!
