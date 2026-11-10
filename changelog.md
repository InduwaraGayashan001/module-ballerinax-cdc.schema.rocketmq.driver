# Changelog

This file contains all the notable changes done to the Ballerina `cdc.schema.rocketmq.driver` package through the releases.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial release of `ballerinax/cdc.schema.rocketmq.driver` — bundles the required Java drivers for RocketMQ-backed CDC schema history storage.

### Changed
- Upgrade the bundled Debezium RocketMQ schema-history storage to 3.5.1.Final.
- Fix the RocketMQ schema history in GraalVM native images by adding the required native-image metadata and turning off fastjson's ASM-based class generation, which native images don't support.
