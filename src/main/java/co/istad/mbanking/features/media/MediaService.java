package co.istad.mbanking.features.media;

import co.istad.mbanking.features.media.dto.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file , String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> files , String folderName);
    List<MediaResponse>  listAllMedia();
    MediaResponse loadMediaByName(String mediaName , String folderName);
    MediaResponse deleteMediaByName(String mediaName , String folderName);
    Resource loadMediaResource(String mediaName , String folderName);
}
