# CustomCapes
How to add custom capes to your client

1. Install MCP (the tutorial is going to be for MCP 910
2. Install optifine for that MCP version
3. Run the client and check it doesn't have bugs
4. Create a folder in the package "assets.minecraft"
5. Put your cape texture (with the format that is on this repository) in that folder
6. Create a class (mine is going to be "CapeController" and do something like this:
  
  public static boolean <name> = true;
  
7. Open the class optifine.CapeUtils
8. Go to the method "downloadCape(final AbstractClientPlayer player)" and copy the code of the java file of this rep
9. Run it!
