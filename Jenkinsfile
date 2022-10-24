
/// SIGMA
TUZ_SIGMA='CAB-SA-CI000602'
TUZ_SIGMA_SSH='CAB-SA-CI000602_ssh'
TAGGER_JOB_NAME_SIGMA="CBP/ekp/cb/update_tag_on_commit_all_branch"
TUZ_SIGMA_PUBLISH_DELTA='CAB-SA-DVO00220'

/// DELTA
TUZ_DELTA='75c4d197-c4a6-42b9-b738-97a2a575d5d0'
TUZ_DELTA_SSH='047d7d69-78f3-474f-9595-eb4681b24a7d'
TAGGER_JOB_NAME_DELTA="PRCRED/EKP-FP/update_tag_on_commit_all_branch"
TUZ_DELTA_PUBLISH_SIGMA='CAB-SA-CI000602'


if (JENKINS_URL.contains('.delta.')) {
    println('Домен определен, как DELTA')
    TUZ_BUILD=TUZ_DELTA
    TUZ_PUBLISH_TO_SIGMA=TUZ_DELTA_PUBLISH_SIGMA
    TUZ_PUBLISH_TO_DELTA=TUZ_DELTA
    TUZ_SSH=TUZ_DELTA_SSH
    TAGGER_JOB_NAME=TAGGER_JOB_NAME_DELTA
} else {
    println('Домен определен, как SIGMA')
    TUZ_BUILD=TUZ_SIGMA
    TUZ_PUBLISH_TO_SIGMA=TUZ_SIGMA
    TUZ_PUBLISH_TO_DELTA=TUZ_SIGMA_PUBLISH_DELTA
    TUZ_SSH=TUZ_SIGMA_SSH
    TAGGER_JOB_NAME=TAGGER_JOB_NAME_SIGMA
}


pipeline {
    options {
        timestamps ()
        buildDiscarder(logRotator(artifactDaysToKeepStr: '30', artifactNumToKeepStr: '50',  daysToKeepStr: '30', numToKeepStr: '50'))
    }


    agent {
        node {
            label 'masterLin'
        }
    }

//     tools {
//         jdk 'openjdk-11.0.11_linux'
//     }


    stages {
        stage(' Check TAGs ') {
            steps {
                script {
                    echo "params.autotest: ${params.autotest}"
                    echo "params.publish: ${params.publish}"
                    echo "env.BRANCH_NAME: ${env.BRANCH_NAME}"
                    echo "env.GIT_BRANCH: ${env.GIT_BRANCH}"
                    echo "env.BRANCH_IS_PRIMARY: ${env.BRANCH_IS_PRIMARY}"
                    echo "env.CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
                    if ( env.BRANCH_NAME.matches('PR-[0-9]+') ) {
                        REAL_BRANCH=env.CHANGE_BRANCH
                        sshagent(["${TUZ_SSH}"]) {
                            script {
                                sh """
                                    git config remote.origin.fetch "+refs/heads/*:refs/remotes/origin/*"
                                    git fetch
                                    git checkout ${env.CHANGE_BRANCH}
                                """
                            }
                        }
                    } else {
                        REAL_BRANCH=env.BRANCH_NAME

                    }
                    echo "REAL_BRANCH: ${REAL_BRANCH}"

                    build job: TAGGER_JOB_NAME, parameters: [
                            string(name: 'COMMIT_BRANCH', value: REAL_BRANCH),
                            string(name: 'COMMIT_REPO_URL', value: env.GIT_URL)
                        ]


                    sshagent(["${TUZ_SSH}"]) {
                        script {
                            sh """
                                git tag | xargs git tag -d
                                git pull --tags
                            """
                        }
                    }
                }
            }
        }

        stage(' Set build name ') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${TUZ_BUILD}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                    script {
                        def BUILD_VERSION = sh(script: './gradlew version -PciUsername=$ciUsername -PciPassword=$ciPassword | grep "Version:" | awk \'{printf $2}\'', returnStdout: true)
                        println "GRADLE VERSION: $BUILD_VERSION"
                        currentBuild.displayName = "${BUILD_VERSION}  ${env.BUILD_ID}"
                    }
                }
            }
        }

        stage(' Build ') {
            steps {
                    withCredentials([usernamePassword(credentialsId: "${TUZ_BUILD}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                        script {
                            if ( REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') || ( env.BRANCH_NAME.matches('PR-[0-9]+') && ( REAL_BRANCH.contains('feature/') || REAL_BRANCH.contains('bugfix/') ) ) ) {
                                echo "BUILD NOW"
                                sh './gradlew clean build -PciUsername=$ciUsername -PciPassword=$ciPassword --refresh-dependencies'
                            } else {
                                echo "DO NOT BUILD THIS TIME"
                            }
                        }
                    }
            }
        }

        stage(' Publish Delta') {
            when {
                expression { REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') || ( env.BRANCH_NAME.matches('PR-[0-9]+') && ( REAL_BRANCH.contains('feature/') || REAL_BRANCH.contains('bugfix/') ) ) }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: "${TUZ_PUBLISH_TO_DELTA}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                    script {
                        ansiColor('xterm') {
                            echo "\033[32m############################## Публикация проекта под ТУЗ = ${TUZ_PUBLISH_TO_DELTA}\033[0m"
                        }
                        sh './gradlew publishAllPublicationsToDeltaRepository -PciUsername=$ciUsername -PciPassword=$ciPassword --info'
                    }
                }
            }
        }

    }
}