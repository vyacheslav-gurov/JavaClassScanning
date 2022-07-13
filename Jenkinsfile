
TAGGER_JOB_NAME="CBP/ekp/cb/update_tag_on_commit_all_branch"
TUZ_BUILD='CAB-SA-CI000602'

pipeline {

    agent {
        node {
            label 'masterLin'
        }
    }

    tools {
        jdk 'openjdk-11.0.11_linux'
    }

    stages {
        stage(' Set TAG ') {
            steps {
                script {
                    echo "env.BRANCH_NAME: ${env.BRANCH_NAME}"
                    echo "env.GIT_BRANCH: ${env.GIT_BRANCH}"
                    echo "env.BRANCH_IS_PRIMARY: ${env.BRANCH_IS_PRIMARY}"
                    echo "env.CHANGE_BRANCH: ${env.CHANGE_BRANCH}"

                    if ( env.BRANCH_NAME.matches('PR-[0-9]+') ) {
                        REAL_BRANCH=env.CHANGE_BRANCH
                        sshagent(['CAB-SA-CI000602_ssh']) {
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
                    build job: TAGGER_JOB_NAME, parameters: [
                            string(name: 'COMMIT_BRANCH', value: REAL_BRANCH),
                            string(name: 'COMMIT_REPO_URL', value: env.GIT_URL)
                        ]

                }
            }
        }

        stage(' Get tags ') {
            steps {
                sshagent(['CAB-SA-CI000602_ssh']) {
                    script {
                        sh """
                            git pull --tags
                        """
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
                            if ( ( env.BRANCH_NAME.matches('PR-[0-9]+') && REAL_BRANCH.contains('feature/') ) || ( env.BRANCH_NAME.matches('PR-[0-9]+') && REAL_BRANCH.contains('bugfix/') ) || REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') ) {
                                echo "BUILD NOW"
                                sh './gradlew clean build -PciUsername=$ciUsername -PciPassword=$ciPassword'
                            } else {
                                echo "DO NOT BUILD THIS TIME"
                            }
                        }
                    }
            }
        }

        stage(' Publish ') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${TUZ_BUILD}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                    script {
                        if ( REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') || ( env.BRANCH_NAME.matches('PR-[0-9]+') && REAL_BRANCH.contains('feature/') ) || ( env.BRANCH_NAME.matches('PR-[0-9]+') && REAL_BRANCH.contains('bugfix/') ) ) {
                            echo "PUBLISH HERE"
                            sh './gradlew publish -PciUsername=$ciUsername -PciPassword=$ciPassword --info'
                        } else {
                            echo "DO NOT PUBLISH FOR THIS BRANCH"
                        }
                    }
                }
            }
        }

    }
}
