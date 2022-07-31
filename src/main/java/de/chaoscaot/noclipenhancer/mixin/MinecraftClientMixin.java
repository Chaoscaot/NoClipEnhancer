package de.chaoscaot.noclipenhancer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Redirect(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z"))
    private boolean isSpecRedirect(ClientPlayerEntity instance) {
        return MinecraftClient.getInstance().interactionManager.getCurrentGameMode() == GameMode.SPECTATOR;
    }

    @Redirect(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isCreative()Z"))
    private boolean isCreativeRedirect(ClientPlayerEntity instance) {
        return MinecraftClient.getInstance().interactionManager.getCurrentGameMode() == GameMode.CREATIVE;
    }
}
