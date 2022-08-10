package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.DTO.QuserSimpleInfoDTO;
import com.ssafy.DTO.userSimpleInfoDTO;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserProjectRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QUserProject qUserProject = QUserProject.userProject;
    QUsers qUsers = QUsers.users;
    QProjects qProjects = QProjects.projects;

    public Optional<List<Users>> getUserListByPid(Long pid) {
        List<Users> userList = jpaQueryFactory.select(qUsers)
                .from(qUserProject)
                .where(qUserProject.projects.pid.eq(pid)).fetch();
        if (userList == null) return Optional.empty();
        return Optional.ofNullable(userList);
    }

    public Optional<List<userSimpleInfoDTO>> getUserSimpleInfoDTOListByPid(Long pid) {
        List<userSimpleInfoDTO> userList = jpaQueryFactory.select(new QuserSimpleInfoDTO(qUserProject.users.uid, qUserProject.users.email, qUserProject.users.nickname))
                .from(qUserProject)
                .where(qUserProject.projects.pid.eq(pid)).fetch();
        if (userList == null) return Optional.empty();
        return Optional.ofNullable(userList);
    }

    public Optional<List<Projects>> getJoinedProjectList(Long uid) {
        List<Projects> projectInfoDTOList = jpaQueryFactory.select(qProjects).from(qUserProject)
                .where(qUserProject.users.uid.eq((uid))).where(qUserProject.projects.activation.eq(true)).fetch();
        if (projectInfoDTOList == null) return Optional.empty();
        return Optional.ofNullable(projectInfoDTOList);
    }


    public Optional<List<Projects>> getDeactivateProjectsByUid(Long uid) {
        List<Projects> projectInfoDTOList = jpaQueryFactory.select(qProjects).from(qUserProject)
                .where(qUserProject.users.uid.eq((uid))).where(qUserProject.projects.activation.eq(false)).fetch();
        if (projectInfoDTOList == null) return Optional.empty();
        return Optional.ofNullable(projectInfoDTOList);

    }
}
