package co.istad.mbanking.features.user;

import co.istad.mbanking.base.BaseResponse;
import co.istad.mbanking.base.BasedMessage;
import co.istad.mbanking.features.user.dto.*;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<UserResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "2") int limit) {
        return userService.findList(page, limit);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteByUuid(@PathVariable String uuid) {
        userService.deleteByUuid(uuid);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        userService.createNew(userCreateRequest);
    }

    @PatchMapping("/{uuid}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable String uuid,
                               @RequestBody UserPasswordRequest userPasswordRequest) {
        userService.changePassword(userPasswordRequest,uuid);
    }
    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserProfile(@PathVariable String uuid,
                         @RequestBody UserUpdateProfileRequest userUpdateProfileRequest) {
        userService.updateUserProfile(userUpdateProfileRequest , uuid);
    }
    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }

    @GetMapping("/{uuid}")
    UserDetailsResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }
    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }
    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableUserByUuid(@PathVariable("uuid") String uuid) {
        userService.enableByUuid(uuid);
    }

    @PutMapping("/{uuid}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUserByUuid(@PathVariable("uuid") String uuid) {
        userService.disableByUuid(uuid);
    }

    @PutMapping("/{uuid}/profile-image")
    BaseResponse<?> updateProfileImage(@PathVariable String uuid,
                                       @RequestBody UserProfileImageRequest userProfileImageRequest) {
        String newProfileImage = userService.updateProfileImage(uuid, userProfileImageRequest.mediaName());

        return BaseResponse.builder()
                .payload(newProfileImage)
                .build();
    }
}

