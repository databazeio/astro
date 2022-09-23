package io.databaze.astro.lang;

import java.util.Arrays;

import com.intellij.lang.html.HTMLLanguage;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IStubFileElementType;

public class AstroFileElementType extends IStubFileElementType<PsiFileStub<?>> {

    private static volatile int stubVersion = -1;

    public AstroFileElementType() {
        super(AstroLanguage.INSTANCE);
    }

    @Override
    public int getStubVersion() {
        return getHtmlStubVersion() + 3;
    }

    public static int getHtmlStubVersion() {
        int version = stubVersion;
        if (version != -1) {
            return version;
        }
        IElementType[] dataElementTypes = IElementType.enumerate(
                (elementType) -> elementType instanceof IStubFileElementType && isAcceptable(elementType));

        int res = Arrays.stream(dataElementTypes).mapToInt((e) -> ((IStubFileElementType<?>) e).getStubVersion()).sum();
        stubVersion = res;

        return res;
    }

    public static boolean isAcceptable(IElementType elementType) {
        String id = elementType.getLanguage().getID();
        return id.equals("JavaScript") || id.equals("CSS");
    }
}
