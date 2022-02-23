# Coursematch

## Dependencies

- Yarn 
- sbt

## Development

1. Run `sbt build`.
2. Run `sbt dev`. 
3. Go to http://localhost:8023.

### Issues

- If you encounter issues with JVM heap running out of space, set the following environment variable.
  In Linux:
  ```
  export SBT_OPTS="-Xmx1536M -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=2G -Xss2M  -Duser.timezone=GMT"
  ```
- If you encounter the following error, set the following environment variable.
  Error
  ```
  [error] node:internal/crypto/hash:67
  [error]   this[kHandle] = new _Hash(algorithm, xofLen);
  [error]                   ^
  [error] Error: error:0308010C:digital envelope routines::unsupported
  ```
  In Linux:
  ```
  export NODE_OPTIONS=--openssl-legacy-provider
  ```

### Linting

- Run `sbt scalafmt` to run the linter and fix formatting issues.git 


