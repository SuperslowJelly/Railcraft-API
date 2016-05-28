/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */
package mods.railcraft.api.core.items;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IToolCrowbar {
    String ORE_TAG = "toolCrowbar";

    /**
     * Controls non-rotational interactions with blocks. Crowbar specific stuff.
     * <p/>
     * Rotational interaction is handled by the Block.rotateBlock() function,
     * which should be called from the Item.onUseFirst() function of your tool.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param pos     the block
     * @return true if can whack a block
     */
    boolean canWhack(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, BlockPos pos);

    /**
     * Callback to do damage to the item.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param pos     the block
     */
    void onWhack(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, BlockPos pos);

    /**
     * Controls whether you can link a cart.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param cart    the cart
     * @return true if can link a cart
     */
    boolean canLink(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, @Nonnull EntityMinecart cart);

    /**
     * Callback to do damage.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param cart    the cart
     */
    void onLink(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, @Nonnull EntityMinecart cart);

    /**
     * Controls whether you can boost a cart.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param cart    the cart
     * @return true if can boost a cart
     */
    boolean canBoost(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, @Nonnull EntityMinecart cart);

    /**
     * Callback to do damage, boosting a cart usually does more damage than
     * normal usage.
     *
     * @param player  the player
     * @param crowbar the crowbar
     * @param cart    the cart
     */
    void onBoost(@Nonnull EntityPlayer player, @Nonnull ItemStack crowbar, @Nonnull EntityMinecart cart);
}
