package com.lulan.shincolle.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.lulan.shincolle.proxy.ClientProxy;
import com.lulan.shincolle.reference.ID;
import com.lulan.shincolle.reference.Reference;
import com.lulan.shincolle.reference.Values;
import com.lulan.shincolle.utility.CalcHelper;
import com.lulan.shincolle.utility.GuiHelper;
import com.lulan.shincolle.utility.LogHelper;

/** draw book text (no picture!)
 *  
 *  book range: left:15,32 ~ 115,172  right:135,32 ~ 235,172
 *  title: center: left:64 right:185 y:38
 *  text: left:13,48 right:135,48 width:100
 *  
 *  for recipe picture:
 *  material pos: (3,3)  (23,3)  (43,3)
 *                (3,23) (23,23) (43,23)     (81,23)
 *                (3,43) (23,43) (43,43)
 */
public class GuiBook {
	
	//book picture
	private static final ResourceLocation guiBookPic01 = new ResourceLocation(Reference.TEXTURES_GUI+"book/BookPic01.png");
	private static final ResourceLocation guiBookPic02 = new ResourceLocation(Reference.TEXTURES_GUI+"book/BookPic02.png");
	
	private static TextureManager tm = ClientProxy.getMineraft().getTextureManager();
	private static GuiContainer gui;
	private static FontRenderer font;
	private static RenderItem itemRender = new RenderItem();
	private static int numChap;
	private static int numPage;
	private static int PageWidth = 106; //page width
	private static int Page0LX = 13;    //left page start X pos
	private static int Page0LY = 50;    //left page start Y pos
	private static int Page0RX = 133;   //right page start X pos
	private static int Page0RY = 50;    //right page start Y pos
	public static final int[] PageLimit = new int[] {1,20,0,0,0,0,0};  //max page number
	
	public GuiBook() {}
	
	/** draw book content */
	public static void drawBookContent(GuiContainer par1, FontRenderer par2, int chap, int page) {
		/** Content Array:
		 *  0:c.type  1:pageL/R  2:posX  3:posY  4:add content
		 */
		int index = chap * 1000 + page;
		List<int[]> cont = Values.BookList.get(index);
		
		gui = par1;
		font = par2;
		numChap = chap;
		numPage = page;
		
		//DEBUG: test page
		if(numChap == 1 && numPage == 8) {
			cont =  Arrays.asList(
					new int[] {0, 0, 0, 0},
					new int[] {0, 1, 0, 0},
					new int[] {1, 0, 0, 0, 0, 100, 72, 100, 62},
					new int[] {2, 0, 3,  3,  ID.Item.AbyssIG},
					new int[] {2, 0, 23, 3,  ID.Item.AbyssIG},
					new int[] {2, 0, 43, 3,  ID.Item.AbyssIG},
					new int[] {2, 0, 3,  23, ID.Item.AbyssIG},
					new int[] {2, 0, 23, 23, ID.Item.AbyssIG},
					new int[] {2, 0, 43, 23, ID.Item.AbyssIG},
					new int[] {2, 0, 23, 43, ID.Item.Stick},
					new int[] {2, 0, 81, 23, ID.Item.KHammer}
		);}
		
		if(cont != null) {
			for(int[] getc : cont) {
				if(getc != null && getc.length > 3) {  //check content existence
					//get draw type
					switch(getc[0]) {
					case 0:    //text
						drawBookText(getc[1], getc[2], getc[3]);
						break;
					case 1:    //picture
						drawBookPic(getc);
						break;
					case 2:    //icon
						drawBookIcon(getc[1], getc[2], getc[3], getc[4]);
						break;
					}
				}
			}//for every item in list
		}//end list null check
	}
	
	/** draw book text
	 *  pageSide: 0:left  1:right
	 *  offXY: x,y offset
	 */
	private static void drawBookText(int pageSide, int offX, int offY) {
		//draw title
		drawTitleText();
		
		//draw page text
		drawPageText(pageSide, offX, offY);
	}
	
	/** draw title text */
	private static void drawTitleText() {
		//get title string
		String str = null;
		switch(numChap) {
		case 0:
			str = I18n.format("gui.shincolle:book.chap"+numChap+".title");
			break;
		default:
			str = I18n.format("gui.shincolle:book.chap"+numChap+".title"+numPage);
			break;
		}
		
		int strlen = (int) (font.getStringWidth(str) * 0.5F);
		str = EnumChatFormatting.UNDERLINE + str;
		//draw title
		font.drawString(str, 64-strlen, 32, GuiHelper.pickColor(GuiHelper.pickColorName.RED2.ordinal()));
	}
	
	/** draw page text */
	private static void drawPageText(int pageSide, int offX, int offY) {
		//set x, y offset
		int picY = Page0LY + offY;        //add y offset
		int picX = Page0LX;               //left page
		if(pageSide > 0) picX = Page0RX;  //right page
		picX += offX;                     //add x offset
		
		//get text string
		String str = I18n.format("gui.shincolle:book.chap"+numChap+".text"+numPage+"d"+pageSide);
		
		//draw text
		drawStringWithSpecialSymbol(str, picX, picY);
	}
	
	/** draw string with new line or other special symbol
	 *  page: 0:left 1:right
	 */
	private static void drawStringWithSpecialSymbol(String str, int x, int y) {
		String[] strArray = CalcHelper.stringConvNewlineToArray(str);
		
		int newY = y;
		for(String s : strArray) {
			//drawSplitString(string, x, y, split width, color)
			font.drawSplitString(s, x, newY, PageWidth, 0);
			newY += font.splitStringWidth(s, PageWidth);
		}
	}
	
	/** draw book picture 
	 *  parms:
	 *  0:c.type  1:page pos  2:posX  3:posY  4:picID  5:picU  6:picV  7:sizeX  8:sizeY
	 */
	private static void drawBookPic(int[] parms) {
		int pageSide, posX, posY, picID, picU, picV, sizeX, sizeY;
		
		//null check
		if(parms != null && parms.length == 9) {
			pageSide = parms[1];
			posX = parms[2];
			posY = parms[3];
			picID = parms[4];
			picU = parms[5];
			picV = parms[6];
			sizeX = parms[7];
			sizeY = parms[8];
		}
		else {
			return;
		}
		
		//set x, y offset
		int picY = Page0LY + posY;        //add y offset
		int picX = Page0LX;               //left page
		if(pageSide > 0) picX = Page0RX;  //right page
		picX += posX;                     //add x offset
		
		//set picture texture
		switch(picID) {
		case 0:
			tm.bindTexture(guiBookPic01);
			break;
		case 1:
			tm.bindTexture(guiBookPic02);
			break;
		}
		//draw picture
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);	//reset RGBA
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		gui.drawTexturedModalRect(picX, picY, picU, picV, sizeX, sizeY);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	/** draw book item icon */
	private static void drawBookIcon(int pageSide, int offX, int offY, int iconID) {
		//set x, y offset
		int picY = Page0LY + offY;        //add y offset
		int picX = Page0LX;               //left page
		if(pageSide > 0) picX = Page0RX;  //right page
		picX += offX;                     //add x offset
		
		drawItemIcon(getItemStackForIcon(iconID), picX, picY, false);
	}
	
	//draw item icon
	private static void drawItemIcon(ItemStack item, int x, int y, boolean effect) {
		if(item != null) {
			itemRender.renderItemIntoGUI(font, tm, item, x, y, effect);
		}
	}
	
	//get max page number
	public static int getMaxPageNumber(int chap) {
		if(chap < PageLimit.length) return PageLimit[chap];
		return 0;
	}
	
	//get itemstack for icon
	public static ItemStack getItemStackForIcon(int itemID) {
		return Values.ItemIconMap.get((byte)itemID);
	}

	

}