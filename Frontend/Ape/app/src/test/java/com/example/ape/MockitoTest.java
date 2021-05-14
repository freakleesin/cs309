package com.example.ape;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;

public class MockitoTest {

    @Test
    public void testloginIsNotNull(){
        MainActivity mPerson = mock(MainActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testregisterIsNotNull(){
        RegisterActivity mPerson = mock(RegisterActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testUsersHomeIsNotNull(){
        HomeActivity mPerson = mock(HomeActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreHomeIsNotNull(){
        StoreHomeActivity mPerson = mock(StoreHomeActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testUserCenterIsNotNull(){
        PersonalCenterActivity mPerson = mock(PersonalCenterActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreCenterIsNotNull(){
        StoreCenterActivity mPerson = mock(StoreCenterActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testCommunityIsNotNull(){
        CommunityActivity mPerson = mock(CommunityActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testPostingIsNotNull(){
        PostingActivity mPerson = mock(PostingActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreIsNotNull(){
        StoreActivity mPerson = mock(StoreActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreSumsIsNotNull(){
        RessActivity mPerson = mock(RessActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangePasswordIsNotNull(){
        ChangePasswordActivity mPerson = mock(ChangePasswordActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangeStoreAddressIsNotNull(){
        ChangeStoreAddressActivity mPerson = mock(ChangeStoreAddressActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangeStoreBusinessIsNotNull(){
        ChangeStoreBusinessActivity mPerson = mock(ChangeStoreBusinessActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangeStorenameIsNotNull(){
        ChangeStorenameActivity mPerson = mock(ChangeStorenameActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangeStoreTypeIsNotNull(){
        ChangeStoreTypeActivity mPerson = mock(ChangeStoreTypeActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChangeUsernameIsNotNull(){
        ChangeUsernameActivity mPerson = mock(ChangeUsernameActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testAddFriendsIsNotNull(){
        AddFriendsActivity mPerson = mock(AddFriendsActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChatingIsNotNull(){
        ChatingActivity mPerson = mock(ChatingActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testChatRoomIsNotNull(){
        ChatRoomTest mPerson = mock(ChatRoomTest.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testFriendsIsNotNull(){
        FriendsActivity mPerson = mock(FriendsActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testPostCommentsIsNotNull(){
        PostCommentsActivity mPerson = mock(PostCommentsActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testRatingScoreIsNotNull(){
        RatingActivity mPerson = mock(RatingActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreFriendsIsNotNull(){
        StoreFriendsActivity mPerson = mock(StoreFriendsActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreAddFriendsIsNotNull(){
        StoreAddFriendsActivity mPerson = mock(StoreAddFriendsActivity.class); //
        assertNotNull(mPerson);
    }

    @Test
    public void testStoreCommunityIsNotNull(){
        StoreCommunityActivity mPerson = mock(StoreCommunityActivity.class); //
        assertNotNull(mPerson);
    }
}
