/*
 * License (MIT)
 *
 * Copyright (c) 2014-2015 Granite Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.granitepowered.granite.impl.entity.living.animal;

import com.google.common.base.Optional;
import org.apache.commons.lang3.NotImplementedException;
import org.granitepowered.granite.impl.meta.GraniteMeta;
import mc.MCEntityHorse;
import mc.MCItemStack;
import org.granitepowered.granite.util.MinecraftUtils;
import org.spongepowered.api.entity.living.animal.Horse;
import org.spongepowered.api.entity.living.animal.HorseColor;
import org.spongepowered.api.entity.living.animal.HorseStyle;
import org.spongepowered.api.entity.living.animal.HorseVariant;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.types.CarriedInventory;

public class GraniteEntityHorse extends GraniteEntityTameable<MCEntityHorse> implements Horse {

    public GraniteEntityHorse(MCEntityHorse obj) {
        super(obj);
    }

    @Override
    public HorseStyle getStyle() {
        //return Granite.getInstance().getGameRegistry().horseStyles.get(((int) obj.fieldGet$dataWatcher().getWatchedObject(20)
        // .fieldGet$watchedObject()) & 0xFF);
        throw new NotImplementedException("");
    }

    @Override
    public void setStyle(HorseStyle horseStyle) {
        obj.dataWatcher.updateObject(20, ((GraniteMeta) getColor()).getType() & 0xFF | ((GraniteMeta) horseStyle).getType() << 8);
    }

    @Override
    public HorseColor getColor() {
        //return Granite.getInstance().getGameRegistry().horseColors.get(((int) obj.fieldGet$dataWatcher().getWatchedObject(20)
        // .fieldGet$watchedObject()) & 0xFF);
        throw new NotImplementedException("");
    }

    @Override
    public void setColor(HorseColor horseColor) {
        obj.dataWatcher.updateObject(20, ((GraniteMeta) horseColor).getType() & 0xFF | ((GraniteMeta) getStyle()).getType() << 8);
    }

    @Override
    public HorseVariant getVariant() {
        //return Granite.getInstance().getGameRegistry().horseVariants.get(obj.fieldGet$dataWatcher().getWatchedObject(19).fieldGet$watchedObject());
        throw new NotImplementedException("");
    }

    @Override
    public void setVariant(HorseVariant horseVariant) {
        obj.dataWatcher.updateObject(19, ((GraniteMeta) horseVariant).getType());
    }

    @Override
    public Optional<ItemStack> getSaddle() {
        ItemStack itemStack = MinecraftUtils.wrap(obj.animalChest.inventoryContents[0]);
        return Optional.fromNullable(itemStack);
    }

    @Override
    public void setSaddle(ItemStack itemStack) {
        if (itemStack.getItem() == ItemTypes.SADDLE) {
            MCItemStack[] inventory = obj.animalChest.inventoryContents;
            inventory[0] = MinecraftUtils.unwrap(itemStack);
            obj.animalChest.inventoryContents = inventory;
        }
    }

    @Override
    public CarriedInventory<? extends Carrier> getInventory() {
        throw new NotImplementedException("");
    }
}