/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.core.items;

import com.google.common.base.Predicate;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * This interface is used with several of the functions in IItemTransfer
 * to provide a convenient means of dealing with entire classes of items without
 * having to specify each item individually.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class StackFilter implements IStackFilter {

    @Override
    public boolean apply(@Nullable final ItemStack input) {
        return true;
    }

    @Override
    public final StackFilter and(@Nonnull final Predicate<? super ItemStack>... other) {
        Objects.requireNonNull(other);
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                for (Predicate<? super ItemStack> filter : other) {
                    if (!filter.apply(stack))
                        return false;
                }
                return StackFilter.this.apply(stack);
            }
        };
    }

    @Override
    public final StackFilter or(@Nonnull final Predicate<? super ItemStack>... other) {
        Objects.requireNonNull(other);
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                for (Predicate<? super ItemStack> filter : other) {
                    if (filter.apply(stack))
                        return true;
                }
                return StackFilter.this.apply(stack);
            }
        };
    }

    @Override
    public final StackFilter negate() {
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                return !StackFilter.this.apply(stack);
            }
        };
    }

    private static StackFilter buildAnd(@Nonnull final Predicate<? super ItemStack>... filters) {
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                Objects.requireNonNull(stack);
                for (Predicate<? super ItemStack> filter : filters) {
                    if (!filter.apply(stack))
                        return false;
                }
                return true;
            }
        };
    }

    private static StackFilter buildOr(@Nonnull final Predicate<? super ItemStack>... filters) {
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                Objects.requireNonNull(stack);
                for (Predicate<? super ItemStack> filter : filters) {
                    if (filter.apply(stack))
                        return true;
                }
                return false;
            }
        };
    }

    private static StackFilter invert(@Nonnull final Predicate<? super ItemStack> filter) {
        return new StackFilter() {
            @Override
            public boolean apply(ItemStack stack) {
                return !filter.apply(stack);
            }
        };
    }

}
