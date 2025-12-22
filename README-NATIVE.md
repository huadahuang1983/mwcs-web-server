# Spring Boot Native 支持说明

## 项目配置

本项目已配置Spring Boot Native支持，使用以下版本：

- Spring Boot: 3.5.8
- Native Build Tools: 0.9.28
- GraalVM 推荐版本: 21.0.2

## 安装 GraalVM

要构建原生镜像，需要先安装GraalVM并配置环境变量。

### macOS 安装步骤

1. 下载 GraalVM 21.0.2 for Java 17:
   ```bash
   # 使用 SDKMAN! (推荐)
   sdk install java 21.0.2-graal
   
   # 或手动下载并安装
   # 从 https://www.graalvm.org/downloads/ 下载适合的版本
   ```

2. 安装 native-image 工具：
   ```bash
   gu install native-image
   ```

3. 验证安装：
   ```bash
   java -version  # 应显示 GraalVM 版本
   native-image --version  # 应显示 native-image 版本
   ```

## 构建原生镜像

### 使用 Maven 构建

```bash
# 构建原生镜像（需要GraalVM环境）
mvn package -Pnative -Dmaven.test.skip=true

# 或使用以下命令构建
mvn native:compile-no-fork
```

### 使用 Buildpacks 构建容器镜像

```bash
# 使用 Buildpacks 构建包含原生镜像的容器
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=mwcs-web-server-native
```

## 运行原生镜像

### 直接运行

```bash
# 运行构建好的原生镜像
./target/mwcs-web-server-native
```

### 运行容器镜像

```bash
# 运行容器镜像
docker run -p 8080:8080 mwcs-web-server-native
```

## 注意事项

1. **GraalVM 依赖**：构建原生镜像必须安装GraalVM和native-image工具
2. **构建时间**：第一次构建原生镜像可能需要较长时间
3. **反射支持**：某些使用反射的代码需要额外配置`@NativeHint`或`reflect-config.json`
4. **动态代理**：使用动态代理的代码也需要额外配置

## 配置说明

### pom.xml 主要配置

- 使用 `native-maven-plugin` 插件处理原生镜像构建
- 配置了 `--no-fallback` 参数确保只构建纯原生镜像

### 自定义配置

如需添加反射配置或其他GraalVM特定配置，可以在 `src/main/resources/META-INF/native-image/` 目录下创建相应的配置文件。

## 参考文档

- [Spring Boot Native Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html)
- [GraalVM Documentation](https://www.graalvm.org/docs/)