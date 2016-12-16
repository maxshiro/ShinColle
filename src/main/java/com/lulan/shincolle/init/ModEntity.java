package com.lulan.shincolle.init;

import com.lulan.shincolle.ShinColle;
import com.lulan.shincolle.entity.destroyer.EntityDestroyerI;
import com.lulan.shincolle.entity.other.EntityAbyssMissile;
import com.lulan.shincolle.item.BasicEntityItem;
import com.lulan.shincolle.utility.LogHelper;

import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
// register natural spawns for entities (1.7.10)
// EntityRegistry.addSpawn(MyEntity.class, spawnProbability, minSpawn, maxSpawn, enumCreatureType, [spawnBiome]);
// See the constructor in BiomeGenBase.java to see the rarity of vanilla mobs; Sheep are probability 10 while Endermen are probability 1
// minSpawn and maxSpawn are about how groups of the entity spawn
// enumCreatureType represents the "rules" Minecraft uses to determine spawning, based on creature type. By default, you have three choices:
//    EnumCreatureType.creature uses rules for animals: spawn everywhere it is light out.
//    EnumCreatureType.monster uses rules for monsters: spawn everywhere it is dark out.
//    EnumCreatureType.waterCreature uses rules for water creatures: spawn only in water.
// [spawnBiome] is an optional parameter of type BiomeGenBase that limits the creature spawn to a single biome type. Without this parameter, it will spawn everywhere. 
// For the biome type you can use an list, but unfortunately the built-in biomeList contains
// null entries and will crash, so you need to clean up that list.
// Diesieben07 suggested the following code to remove the nulls and create list of all biomes:
// BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);
// example
// EntityRegistry.addSpawn(EntityLion.class, 6, 1, 5, EnumCreatureType.creature, BiomeGenBase.savanna); //change the values to vary the spawn rarity, biome, etc.              
// EntityRegistry.addSpawn(EntityElephant.class, 10, 1, 5, EnumCreatureType.creature, BiomeGenBase.savanna); //change the values to vary the spawn rarity, biome, etc.              
*/
public class ModEntity
{
	
	private static int modEntityID = 1;  //start id

	
	public static void init()
	{
		/** entity */
		//register ship entity
//		createEntity(EntityAirfieldHime.class, "EntityAirfieldHime", modEntityID++);
//		createEntity(EntityBattleshipHime.class, "EntityBattleshipHime", modEntityID++);
//		createEntity(EntityBattleshipNGT.class, "EntityBattleshipNGT", modEntityID++);
//		createEntity(EntityBattleshipNGTBoss.class, "EntityBattleshipNGTBoss", modEntityID++);
//		createEntity(EntityBattleshipYMT.class, "EntityBattleshipYMT", modEntityID++);
//		createEntity(EntityBattleshipYMTBoss.class, "EntityBattleshipYMTBoss", modEntityID++);
//		createEntity(EntityBattleshipRe.class, "EntityBattleshipRe", modEntityID++);
//		createEntity(EntityBattleshipTa.class, "EntityBattleshipTa", modEntityID++);
//		createEntity(EntityCarrierAkagi.class, "EntityCarrierAkagi", modEntityID++);
//		createEntity(EntityCarrierAkagiBoss.class, "EntityCarrierAkagiBoss", modEntityID++);
//		createEntity(EntityCarrierKaga.class, "EntityCarrierKaga", modEntityID++);
//		createEntity(EntityCarrierKagaBoss.class, "EntityCarrierKagaBoss", modEntityID++);
//		createEntity(EntityCarrierHime.class, "EntityCarrierHime", modEntityID++);
//		createEntity(EntityCarrierWD.class, "EntityCarrierWD", modEntityID++);
//		createEntity(EntityCarrierWo.class, "EntityCarrierWo", modEntityID++);
		createEntity(EntityDestroyerI.class, "EntityDestroyerI", modEntityID++);
//		createEntity(EntityDestroyerRo.class, "EntityDestroyerRo", modEntityID++);
//		createEntity(EntityDestroyerHa.class, "EntityDestroyerHa", modEntityID++);
//		createEntity(EntityDestroyerNi.class, "EntityDestroyerNi", modEntityID++);
//		createEntity(EntityDestroyerAkatsuki.class, "EntityDestroyerAkatsuki", modEntityID++);
//		createEntity(EntityDestroyerAkatsukiMob.class, "EntityDestroyerAkatsukiMob", modEntityID++);
//		createEntity(EntityDestroyerHibiki.class, "EntityDestroyerHibiki", modEntityID++);
//		createEntity(EntityDestroyerHibikiMob.class, "EntityDestroyerHibikiMob", modEntityID++);
//		createEntity(EntityDestroyerIkazuchi.class, "EntityDestroyerIkazuchi", modEntityID++);
//		createEntity(EntityDestroyerIkazuchiMob.class, "EntityDestroyerIkazuchiMob", modEntityID++);
//		createEntity(EntityDestroyerInazuma.class, "EntityDestroyerInazuma", modEntityID++);
//		createEntity(EntityDestroyerInazumaMob.class, "EntityDestroyerInazumaMob", modEntityID++);
//		createEntity(EntityDestroyerShimakaze.class, "EntityDestroyerShimakaze", modEntityID++);
//		createEntity(EntityDestroyerShimakazeBoss.class, "EntityDestroyerShimakazeBoss", modEntityID++);
//		createEntity(EntityHarbourHime.class, "EntityHarbourHime", modEntityID++);
//		createEntity(EntityHeavyCruiserRi.class, "EntityHeavyCruiserRi", modEntityID++);
//		createEntity(EntityHeavyCruiserNe.class, "EntityHeavyCruiserNe", modEntityID++);
//		createEntity(EntityNorthernHime.class, "EntityNorthernHime", modEntityID++);
//		createEntity(EntityRensouhou.class, "EntityRensouhou", modEntityID++);
//		createEntity(EntityRensouhouBoss.class, "EntityRensouhouBoss", modEntityID++);
//		createEntity(EntityRensouhouS.class, "EntityRensouhouS", modEntityID++);
//		createEntity(EntitySubmKa.class, "EntitySubmKa", modEntityID++);
//		createEntity(EntitySubmYo.class, "EntitySubmYo", modEntityID++);
//		createEntity(EntitySubmSo.class, "EntitySubmSo", modEntityID++);
//		createEntity(EntitySubmRo500.class, "EntitySubmRo500", modEntityID++);
//		createEntity(EntitySubmRo500Mob.class, "EntitySubmRo500Mob", modEntityID++);
//		createEntity(EntitySubmU511.class, "EntitySubmU511", modEntityID++);
//		createEntity(EntitySubmU511Mob.class, "EntitySubmU511Mob", modEntityID++);
//		createEntity(EntityTransportWa.class, "EntityTransportWa", modEntityID++);
		
		//register mount entity
//		createEntity(EntityMountAfH.class, "EntityMountAfH", modEntityID++);
//		createEntity(EntityMountBaH.class, "EntityMountBaH", modEntityID++);
//		createEntity(EntityMountCaH.class, "EntityMountCaH", modEntityID++);
//		createEntity(EntityMountCaWD.class, "EntityMountCaWD", modEntityID++);
//		createEntity(EntityMountHbH.class, "EntityMountHbH", modEntityID++);
//		createEntity(EntityMountSeat.class, "EntityMountSeat2", modEntityID++);
		
		//register projectile entity
		createProjectileEntity(EntityAbyssMissile.class, "EntityAbyssMissile", modEntityID++);
//		createProjectileEntity(EntityAirplane.class, "EntityAirplane", modEntityID++);
//		createProjectileEntity(EntityAirplaneTakoyaki.class, "EntityAirplaneTakoyaki", modEntityID++);
//		createProjectileEntity(EntityAirplaneT.class, "EntityAirplaneT", modEntityID++);
//		createProjectileEntity(EntityAirplaneZero.class, "EntityAirplaneZero", modEntityID++);
//		createProjectileEntity(EntityAirplaneTHostile.class, "EntityAirplaneTMob", modEntityID++);
//		createProjectileEntity(EntityAirplaneZeroHostile.class, "EntityAirplaneZeroMob", modEntityID++);
//		createProjectileEntity(EntityFloatingFort.class, "EntityFloatingFort", modEntityID++);
//		createProjectileEntity(EntityProjectileBeam.class, "EntityProjectileBeam", modEntityID++);
		
		//register render entity
//		createProjectileEntity(EntityRenderFlare.class, "EntityRenderFlare", modEntityID++);

		//register item, misc entity
		createItemEntity(BasicEntityItem.class, "BasicEntityItem", modEntityID++);
		
	}
	
//	/** mob自然生成方法, 必須放在postInit才呼叫, 以取得全部mod註冊的全部biome
//	 *  prob: witch = 5, enderman = 10, zombie = 100
//	 */
//	public static void initNaturalSpawn()
//	{
//		//spawn in ALL ocean biome
//		BiomeGenBase[] allBiomes = Iterators.toArray(Iterators.filter(Iterators.forArray(BiomeGenBase.getBiomeGenArray()), Predicates.notNull()), BiomeGenBase.class);
//		EnumCreatureType spawnType = new EnumCreatureType(BasicEntityShipHostile.class, 5, Material.water, true, false);
//		LogHelper.info("AAAAAAAAAAAAA "+allBiomes.length);
//		
//		
//		for (int i = 0; i < allBiomes.length; ++i)
//		{
//			if(BiomeDictionary.isBiomeOfType(allBiomes[i], BiomeDictionary.Type.WATER) ||
//			   BiomeDictionary.isBiomeOfType(allBiomes[i], BiomeDictionary.Type.BEACH))
//			{
//				EntityRegistry.addSpawn(EntitySubmU511Mob.class, 10, 1, 1, spawnType, allBiomes[i]);
//			}
//		}
//	}
	
	//登錄生物方法
	//參數: 該生物class, 生物名稱, 生物id
	public static void createEntity(Class entityClass, String entityName, int entityId)
	{
		LogHelper.info("DEBUG : register entity: "+entityId+" "+entityClass+" "+entityName);
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送同步封包(高速entity必須true才會顯示平順)
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 64, 1, true);
	}
	
	//登錄非生物方法 (無生怪蛋)
	//參數: 該生物class, 生物名稱, 生物id
	public static void createProjectileEntity(Class entityClass, String entityName, int entityId)
	{
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送速度封包
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 64, 1, true);
	}
	
	//登錄item entity方法 (無生怪蛋)
	//參數: 該生物class, 生物名稱, 生物id
	public static void createItemEntity(Class entityClass, String entityName, int entityId)
	{
		//登錄參數: 生物class, 生物名稱, 生物id, mod副本, 追蹤更新距離, 更新時間間隔, 是否發送速度封包
		EntityRegistry.registerModEntity(entityClass, entityName, entityId, ShinColle.instance, 64, 4, false);
	}
	
	
}
