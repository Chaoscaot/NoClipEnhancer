package de.chaoscaot.noclipenhancer.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mouse.class)
public class ScrollMixin {

    // Scrolling
    @Redirect(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z"))
    private boolean isSpectatorRedirect(ClientPlayerEntity instance) {
        return MinecraftClient.getInstance().interactionManager.getCurrentGameMode() == GameMode.SPECTATOR;
    }

    // Mouse Button
    @Redirect(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z"))
    private boolean isSpecRedirect(ClientPlayerEntity instance) {
        return MinecraftClient.getInstance().interactionManager.getCurrentGameMode() == GameMode.SPECTATOR;
    }
}
