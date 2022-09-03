package br.com.tagid.tagid.Service;

import br.com.tagid.tagid.Entities.Tag;
import br.com.tagid.tagid.Repository.TagIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagIdService {
    @Autowired
    private TagIdRepository repository;

    public void insertTagId(Tag tagId) {
        try {
            Tag tag = new Tag(tagId.getTagId());
            repository.save(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Tag> listTags () {
        List<Tag> tags = null;
        try {
            tags = repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    public int updateTag(Tag tag, String tagId) {
        try {
            Tag uTag = repository.findById(tagId).orElse(null);
            if (uTag ==null) throw new Exception();

            uTag.setStatus(tag.isStatus());
            uTag.setDescription(tag.getDescription());

            repository.save(uTag);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public int deleteTag (String id) {
        try {
            if (!repository.existsById(id)) throw new Exception();
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    public boolean isAuthorized (String tagId) {
        try {
            Tag tag = repository.findById(tagId).orElse(null);
            return tag != null && tag.isStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Tag> findTagsByStatus (boolean status) {
        List<Tag> tags = null;
        try {
            tags = repository.findByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }
}
