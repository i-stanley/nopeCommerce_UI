package org.nopeCommerce_ui.core;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureVideoExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        if (context.getExecutionException().isEmpty()) {
            VideoContext.clear();
            return;
        }

        String videoName = VideoContext.getVideoName();
        VideoContext.clear();

        if (videoName == null || videoName.isBlank()) return;

        // This is where Selenoid writes videos (see docker-compose volume)
        Path videoPath = Path.of("/tmp/selenoid-video", videoName);

        if (!Files.exists(videoPath)) return;

        try (InputStream is = Files.newInputStream(videoPath)) {
            Allure.addAttachment("Video", "video/mp4", is, ".mp4");
        }
    }
}
