# Changelog

This file contains all the notable changes done to the Ballerina `cdc.schema.rocketmq.driver` package through the releases.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Changed
- Upgrade Netty to 4.2.20.Final, which fixes GraalVM native image builds on Java 25 failing in `io.netty.util.internal.CleanerJava24Linker` (its FFM `malloc`/`free` downcalls are not supported in a native image).

## [1.2.1]

### Added
- [[#9275] Add package icon for the packages missing a logo in the Integration Store](https://github.com/ballerina-platform/ballerina-library/issues/9275)
- Initial release of `ballerinax/cdc.schema.rocketmq.driver` — bundles the required Java drivers for RocketMQ-backed CDC schema history storage.

### Changed
- Upgrade the bundled Debezium RocketMQ schema-history storage to 3.5.1.Final.
- Migrate to Ballerina Swan Lake Update 14 (2201.14.0) with Java 25 support.
- Ship the Netty 4.2.18.Final artifacts that `rocketmq-remoting` needs, instead of relying on Netty being supplied by another package at runtime ([#9152](https://github.com/ballerina-platform/ballerina-library/issues/9152)).
- Upgrade gRPC to 1.84.2, whose `grpc-netty-shaded` bundles Netty 4.2, and protobuf-java to 3.25.9.
- Fix the RocketMQ schema history in GraalVM native images by adding the required native-image metadata and turning off fastjson's ASM-based class generation, which native images don't support.
- Upgrade Guava to 33.7.2-jre to address [CVE-2026-102554](https://avd.aquasec.com/nvd/cve-2026-102554).
