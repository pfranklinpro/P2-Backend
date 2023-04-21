package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewUserRequest;
import franklin.paul.sylvester.dtos.requests.UpdateProfileRequest;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.entities.UserProfile;
import franklin.paul.sylvester.repositories.UserProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserProfileServiceTest {
    private UserProfileService sut;
    private final UserProfileRepository mockProfileRepo = Mockito.mock(UserProfileRepository.class);

    @Before
    public void init() {
        sut = new UserProfileService(mockProfileRepo);
    }

    @Test
    public void test_correctCreateProfile_givenRequest() {
        // Arrange
        UserProfileService spySut = Mockito.spy(sut);
        NewUserRequest req = new NewUserRequest("testUsername", "mRMEY476", "mRMEY476",
                "testUsername@testUsername.com", "testDisplayName",
                LocalDate.of(2022,12,13));

        User user = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        UserProfile userProfile = new UserProfile("0", req.getDisplayName(), null, req.getBirthDate(),
                null, null, null, user);

        Mockito.when(spySut.createProfile(req, user)).thenReturn(userProfile);

        // Act
        UserProfile newUserProfile = spySut.createProfile(req, user);

        // Assert
        assertEquals("0",newUserProfile.getProfileId());
        assertEquals(req.getDisplayName(),newUserProfile.getDisplayName());
        assertNull(newUserProfile.getLocation());
        assertEquals(req.getBirthDate(),newUserProfile.getBirthDate());
        assertNull(newUserProfile.getOccupation());
        assertNull(newUserProfile.getBio());
        assertNull(newUserProfile.getProfilePicUrl());

        User newUser = newUserProfile.getUser();

        assertEquals("0",newUser.getUserId());
        assertEquals("testUsername",newUser.getUsername());
        assertEquals("mRMEY476",newUser.getPassword());
        assertEquals("testUsername@testUsername.com",newUser.getEmail());
        assertEquals(new Date(2022,12,13),newUser.getRegistered());
        assertNull(newUser.getRoleId());
    }

    @Test
    public void test_correctGetProfileByUserId_givenUserId() {
        // Arrange
        UserProfileService sut1 = Mockito.spy(sut);
        User user = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        UserProfile userProfile = new UserProfile("0", "testDisplayName", null,
                LocalDate.of(2022,12,13), null, null, null, user);

        Mockito.when(mockProfileRepo.findByUserId("0")).thenReturn(userProfile);
        Mockito.when(sut1.getProfileByUserId("0")).thenReturn(userProfile);

        // Act
        UserProfile newUserProfile = sut1.getProfileByUserId("0");

        // Assert
        assertEquals("0",newUserProfile.getProfileId());
        assertEquals("testDisplayName",newUserProfile.getDisplayName());
        assertNull(newUserProfile.getLocation());
        assertEquals(LocalDate.of(2022,12,13),newUserProfile.getBirthDate());
        assertNull(newUserProfile.getOccupation());
        assertNull(newUserProfile.getBio());
        assertNull(newUserProfile.getProfilePicUrl());

        User newUser = newUserProfile.getUser();

        assertEquals("0",newUser.getUserId());
        assertEquals("testUsername",newUser.getUsername());
        assertEquals("mRMEY476",newUser.getPassword());
        assertEquals("testUsername@testUsername.com",newUser.getEmail());
        assertEquals(new Date(2022,12,13),newUser.getRegistered());
        assertNull(newUser.getRoleId());
    }

    @Test
    public void test_correctIsEmptyDisplayName_giveDisplayName() {
        // Arrange

        // Act
        Boolean notEmptyDisplayName = sut.isEmptyDisplayName("testDisplayName");
        Boolean emptyDisplayName = sut.isEmptyDisplayName("");

        // Assert
        assertEquals(false,notEmptyDisplayName);
        assertEquals(true,emptyDisplayName);
    }

    @Test
    public void test_correctIsValidBirthDate_givenBirthDate() {
        // Arrange

        // Act
        Boolean validBirthDate = sut.isValidBirthDate(LocalDate.of(2000, 12, 13));
        Boolean invalidBirthDate = sut.isValidBirthDate(LocalDate.of(2022, 12, 13));

        // Assert
        assertEquals(true,validBirthDate);
        assertEquals(false,invalidBirthDate);
    }

    @Test
    public void test_correctUpdateProfile_givenRequestAndProfileId() {
        // Arrange
        UserProfileService spySut = Mockito.spy(sut);
        UpdateProfileRequest req = new UpdateProfileRequest("new display name", "San Antonio, TX",
                LocalDate.of(1995, 11, 10), "software engineer", "sample bio",
                null);

        String profileId = UUID.randomUUID().toString();

        // Act
        spySut.updateProfile(req, profileId);

        // Assert
        Mockito.verify(mockProfileRepo, Mockito.times(1)).update(req.getDisplayName(), req.getLocation(),
                req.getBirthDate(), req.getOccupation(), req.getBio(), req.getProfilePicUrl(), profileId);
    }
}
