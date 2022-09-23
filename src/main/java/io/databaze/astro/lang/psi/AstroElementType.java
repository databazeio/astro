package io.databaze.astro.lang.psi;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.xml.HtmlFileElementType;

import io.databaze.astro.lang.AstroFileElementType;

public interface AstroElementType {
    IFileElementType ASTRO_FILE = new AstroFileElementType();
}
