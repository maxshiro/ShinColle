package com.lulan.shincolle.client.particle;

import org.lwjgl.opengl.GL11;

import com.lulan.shincolle.reference.Reference;
import com.lulan.shincolle.utility.LogHelper;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


/**TEAM CIRCLE PARTICLE
 * ���team��諸�ؼ�, �I�����ؼ�, �౱��ؼ�
 * type:
 * 0: ���, �ثe�Ҧ��b������ܤֳ��|��ܺ�����
 * 1: �C��, pointer��single mode�ɷ|����ؼ�
 * 2: ����, pointer��group mode�|����ؼ�
 * 3: ����, pointer��formation mode�|����ؼ�
 * 4: ���, moving target (show 20 ticks), alpha fade out
 * 5: ����, attack target (show 20 ticks), alpha fade out
 */
@SideOnly(Side.CLIENT)
public class EntityFXTeam extends EntityFX {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.TEXTURES_PARTICLE+"EntityFXTeam.png");
	private int particleType;	//0:green 1:cyan 2:red 3:yellow
	private double height;
	private Entity host;
	
    public EntityFXTeam(World world, Entity host, float scale, int type) {
        super(world, host.posX, host.posY, host.posZ, 0.0D, 0.0D, 0.0D);  
        this.host = host;
        this.height = host.height;
        this.motionX = 0D;
        this.motionY = 0D;
        this.motionZ = 0D;
        this.particleScale = scale;
        this.particleAlpha = 1F;
        this.noClip = true;
        this.particleType = type;
        
        switch(type) {
        default:	//green, normal mode
        	this.particleRed = 0F;
        	this.particleGreen = 1F;
        	this.particleBlue = 0F;
        	this.particleMaxAge = 9;
        	break;
        case 1:		//cyan, single mode
        	this.particleRed = 0F;
        	this.particleGreen = 1F;
        	this.particleBlue = 1F;
        	this.particleMaxAge = 9;
        	break;
        case 2:		//red, group mode
        	this.particleRed = 1F;
        	this.particleGreen = 0F;
        	this.particleBlue = 0F;
        	this.particleMaxAge = 9;
        	break;
        case 3:		//yellow, formation mode
        	this.particleRed = 1F;
        	this.particleGreen = 0.9F;
        	this.particleBlue = 0F;
        	this.particleMaxAge = 9;
        	break;
        }
    }
    
    public EntityFXTeam(World world, float scale, int type, double x, double y, double z) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.motionX = 0D;
        this.motionY = 0D;
        this.motionZ = 0D;
        this.height = 1.5D;
        this.particleScale = scale;
        this.particleAlpha = 1F;
        this.noClip = true;
        this.particleType = type;
        
        switch(type) {
        default:	//green, normal mode
        case 4:		//green, moving target
        	this.particleRed = 0F;
        	this.particleGreen = 1F;
        	this.particleBlue = 0F;
        	this.particleMaxAge = 20;
        	this.setPosition(x, y, z);
        	break;
        case 5:		//red, attack target
        	this.particleRed = 1F;
        	this.particleGreen = 0F;
        	this.particleBlue = 0F;
        	this.particleMaxAge = 20;
        	this.setPosition(x, y, z);
        	break;
        }
    }

    public void renderParticle(Tessellator tess, float ticks, float par3, float par4, float par5, float par6, float par7) {
		GL11.glPushMatrix();
		//�ϥΦ۱a���K����
		Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		float xmin = 0F;
		float xmax = 1F;
		float y1min = 0F;	//�b�Y�Ϯ�
		float y1max = 0.5F;
		float y2min = 0.5F;	//���Ϯ�
		float y2max = 1F;
		float halfScale = particleScale * 0.5F;
		
		//particle�O�H���a������render, �]���y�Эn����interpPos�ഫ�����a�����y��
        double f11 = (float)(this.posX - interpPosX);
        double f12 = (float)(this.posY - interpPosY + this.height + 1.3D);
        double f12b = (float)(this.posY - interpPosY + 0.3D);
        double f13 = (float)(this.posZ - interpPosZ);
 
        //start tess
        tess.startDrawingQuads();
        tess.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        tess.setBrightness(240);
        //�e�X�b�Y
        //X��Z��m���[�Y����ʰ���, �u��Y�b�|���V���a��V
        tess.addVertexWithUV((double)(f11 - par3 * particleScale), (double)(f12 - par4 * particleScale * 2.0F), (double)(f13 - par5 * particleScale), xmax, y1max);
        tess.addVertexWithUV((double)(f11 - par3 * particleScale), (double)(f12 + par4 * particleScale * 2.0F), (double)(f13 - par5 * particleScale), xmax, y1min);
        tess.addVertexWithUV((double)(f11 + par3 * particleScale), (double)(f12 + par4 * particleScale * 2.0F), (double)(f13 + par5 * particleScale), xmin, y1min);
        tess.addVertexWithUV((double)(f11 + par3 * particleScale), (double)(f12 - par4 * particleScale * 2.0F), (double)(f13 + par5 * particleScale), xmin, y1max);

        halfScale = particleScale * 3F;
        
        tess.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha * 0.5F);
        //�e�X���(�¤W)
        tess.addVertexWithUV((double)(f11 + halfScale), f12b, (double)(f13 + halfScale), xmax, y2max);
        tess.addVertexWithUV((double)(f11 + halfScale), f12b, (double)(f13 - halfScale), xmax, y2min);
        tess.addVertexWithUV((double)(f11 - halfScale), f12b, (double)(f13 - halfScale), xmin, y2min);
        tess.addVertexWithUV((double)(f11 - halfScale), f12b, (double)(f13 + halfScale), xmin, y2max);

        //�e�X���(�¤U)
        tess.addVertexWithUV((double)(f11 + halfScale), f12b, (double)(f13 - halfScale), xmax, y2max);
        tess.addVertexWithUV((double)(f11 + halfScale), f12b, (double)(f13 + halfScale), xmax, y2min);
        tess.addVertexWithUV((double)(f11 - halfScale), f12b, (double)(f13 + halfScale), xmin, y2min);
        tess.addVertexWithUV((double)(f11 - halfScale), f12b, (double)(f13 - halfScale), xmin, y2max);
        
        //stop tess for restore texture
        tess.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glPopMatrix();
    }
    
    //layer: 0:particle 1:terrain 2:items 3:custom?
    @Override
    public int getFXLayer() {
        return 3;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
    	if(host != null) {
			this.setPosition(host.posX, host.posY, host.posZ);
    	}
    	else {
    		if(particleType < 4) {
    			this.setDead();
    		}
    	}
    	
    	//fade out effect
    	if(particleType > 3 && particleAge > 10) {
    		this.particleAlpha = 1F - (((float)particleAge - 10F) / 20F);
    	}
    	
        if(this.particleAge++ > this.particleMaxAge) {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }
}
