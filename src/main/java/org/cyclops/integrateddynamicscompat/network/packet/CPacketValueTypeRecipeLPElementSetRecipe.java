package org.cyclops.integrateddynamicscompat.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.cyclops.cyclopscore.network.CodecField;
import org.cyclops.cyclopscore.network.PacketCodec;
import org.cyclops.integrateddynamics.core.logicprogrammer.ValueTypeRecipeLPElement;
import org.cyclops.integrateddynamics.inventory.container.ContainerLogicProgrammerBase;

import java.util.List;

/**
 * @author rubensworks
 */
public class CPacketValueTypeRecipeLPElementSetRecipe extends PacketCodec {

    @CodecField
    private int windowId;
    @CodecField
    private List<ItemStack> itemInputs;
    @CodecField
    private List<FluidStack> fluidInputs;
    @CodecField
    private List<ItemStack> itemOutputs;
    @CodecField
    private List<FluidStack> fluidOutputs;

    public CPacketValueTypeRecipeLPElementSetRecipe() {

    }

    public CPacketValueTypeRecipeLPElementSetRecipe(int windowId, List<ItemStack> itemInputs, List<FluidStack> fluidInputs,
                                                    List<ItemStack> itemOutputs, List<FluidStack> fluidOutputs) {
        this.windowId = windowId;
        this.itemInputs = itemInputs;
        this.fluidInputs = fluidInputs;
        this.itemOutputs = itemOutputs;
        this.fluidOutputs = fluidOutputs;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void actionClient(World world, EntityPlayer player) {

    }

    @Override
    public void actionServer(World world, EntityPlayerMP player) {
        if(player.openContainer.windowId == windowId) {
            ContainerLogicProgrammerBase container = (ContainerLogicProgrammerBase) player.openContainer;
            ValueTypeRecipeLPElement element = (ValueTypeRecipeLPElement) container.getActiveElement();
            element.setRecipeGrid(container, itemInputs, fluidInputs, itemOutputs, fluidOutputs);
        }
    }

}
