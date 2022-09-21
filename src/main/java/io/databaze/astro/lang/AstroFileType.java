package io.databaze.astro.lang;

import javax.swing.Icon;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.fileTypes.LanguageFileType;

import icons.AstroIcons;

public class AstroFileType extends LanguageFileType {
    public static final LanguageFileType INSTANCE = new AstroFileType();

    public AstroFileType() {
        super(AstroLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Astro";
    }

    @Override
    public @NotNull String getDescription() {
        return "Astro";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "astro";
    }

    @Override
    public @Nullable Icon getIcon() {
        return AstroIcons.Astro;
    }
}
