/*
 * Copyright (c) 2026, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerina.lib.cdc.schema.rocketmq.driver;

/**
 * This class will hold utility functions needed when running as a GraalVM native image.
 */
public class NativeImageUtils {

    private static final String IMAGE_CODE_PROPERTY = "org.graalvm.nativeimage.imagecode";
    private static final String[] FASTJSON_CONFIG_CLASSES = {
            "com.alibaba.fastjson.parser.ParserConfig",
            "com.alibaba.fastjson.serializer.SerializeConfig"
    };

    private NativeImageUtils() {
    }

    /**
     * fastjson 1.x (used by the RocketMQ client) generates serializer classes at runtime with ASM, which is not
     * supported in a native image. Make it fall back to reflection when running as a native image.
     */
    public static void disableFastjsonAsmInNativeImage() {
        if (!"runtime".equals(System.getProperty(IMAGE_CODE_PROPERTY))) {
            return;
        }
        for (String className : FASTJSON_CONFIG_CLASSES) {
            try {
                Class<?> configClass = Class.forName(className);
                Object globalInstance = configClass.getMethod("getGlobalInstance").invoke(null);
                configClass.getMethod("setAsmEnable", boolean.class).invoke(globalInstance, false);
            } catch (ReflectiveOperationException | LinkageError e) {
                // fastjson is not on the classpath, so there is nothing to disable.
            }
        }
    }
}
