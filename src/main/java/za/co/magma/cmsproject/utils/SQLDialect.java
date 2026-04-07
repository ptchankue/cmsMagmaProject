package za.co.magma.cmsproject.utils;

// Simple marker class kept for backwards compatibility when referenced in configuration.
// The real SQL dialect will be selected by Hibernate automatically. Do not register
// custom Hibernate functions here because Hibernate 7 API changed significantly.
public class SQLDialect {
  // intentionally empty
}