package sp.infrastructure.persistence.converter;

public class DomainEntityConverter {

 /* @Component
  public static class PrivilegeEntityToPrivilegeValue implements Converter<PrivilegeEntity, Privilege> {

    @Override
    public Privilege convert(PrivilegeEntity privilegeEntity) {
      return Privilege.get(privilegeEntity.getId());
    }

  }

  @Component
  public static class PrivilegeValueToPrivilegeEntity implements Converter<Privilege, PrivilegeEntity> {

    @Override
    public PrivilegeEntity convert(Privilege privilege) {
      return new PrivilegeEntity().setId(privilege.getId()).setPrivilege(privilege);
    }
  }

  @Component
  public static class PrivilegeEntitySetToPrivilegeValueSet implements Converter<Set<PrivilegeEntity>, Set<Privilege>> {

    @Override
    public Set<Privilege> convert(Set<PrivilegeEntity> privilegeEntities) {
      return privilegeEntities.parallelStream()
                .map(PrivilegeEntity::getPrivilege).collect(Collectors.toSet());
    }
  }

  @Component
  public static class PrivilegeValueSetToPrivilegeEntitySet implements Converter<Set<Privilege>, Set<PrivilegeEntity>> {

    @Override
    public Set<PrivilegeEntity> convert(Set<Privilege> privileges) {
      return privileges.parallelStream()
                .map(privilege -> new PrivilegeValueToPrivilegeEntity().convert(privilege))
                .collect(Collectors.toSet());
    }
  }

  @Component
  public static class ProfileEntityToProfile implements Converter<ProfileEntity, Profile> {

    @Override
    public Profile convert(ProfileEntity profileEntity) {
      return new Profile(
                profileEntity.getId(),
                profileEntity.getName(),
                new PrivilegeEntitySetToPrivilegeValueSet().convert(profileEntity.getPrivilegeEntitySet())
            );
    }
  }

  @Component
  public static class ProfileToProfileEntity implements Converter<Profile, ProfileEntity> {

    @Override
    public ProfileEntity convert(Profile profile) {
      return new ProfileEntity().setId(profile.id()).setName(profile.name())
                .setPrivilegeEntitySet(
                    new PrivilegeValueSetToPrivilegeEntitySet().convert(profile.privileges())
                );
    }
  }

  @Component
  public static class ProfileEntitySetToProfileSet implements Converter<Set<ProfileEntity>, Set<Profile>> {

    @Override
    public Set<Profile> convert(Set<ProfileEntity> profileEntities) {
      return profileEntities.parallelStream()
                .map(profileEntity -> new ProfileEntityToProfile().convert(profileEntity))
                .collect(Collectors.toSet());
    }
  }

  @Component
  public static class ProfileSetToProfileEntitySet implements Converter<Set<Profile>, Set<ProfileEntity>> {

    @Override
    public Set<ProfileEntity> convert(Set<Profile> profiles) {
      return profiles.parallelStream()
                .map(profileEntity -> new ProfileToProfileEntity().convert(profileEntity))
                .collect(Collectors.toSet());
    }
  }

  @Component
  public static class UserEntityToUser implements Converter<UserEntity, User> {

    @Override
    public User convert(UserEntity userEntity) {
      return new User(userEntity.getId(),
          userEntity.getUserName(),
          userEntity.getPassword(),
          userEntity.isEnabled(),
          new ProfileEntitySetToProfileSet().convert(userEntity.getProfileEntitySet()));
    }
  }

  @Component
  public static class UserToUserEntity implements Converter<User, UserEntity> {

    @Override
    public UserEntity convert(User user) {
      return new UserEntity().setId(user.id()).setPassword(user.password())
      .setUserName(user.name()).setEnabled(user.enabled())
      .setProfileEntitySet(new ProfileSetToProfileEntitySet().convert(user.profiles()));
    }
  }

  @Component
  public static class OrgEntityToOrganization implements Converter<OrgEntity, Organization> {

    @Override
    public Organization convert(OrgEntity orgEntity) {
      return new Organization(orgEntity.getId(),
      orgEntity.getName());
    }
  }*/

}
