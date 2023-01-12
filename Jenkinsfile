
TUZ_BUILD='0cabf632-0cb7-41cc-9dda-4a1b4a903f48'
TUZ_SSH='047d7d69-78f3-474f-9595-eb4681b24a7d'
TAGGER_JOB_NAME="PRCRED/EKP-FP/update_tag_on_commit_all_branch"

pipeline {
    options {
        timestamps ()
        buildDiscarder(logRotator(artifactDaysToKeepStr: '30', artifactNumToKeepStr: '50',  daysToKeepStr: '30', numToKeepStr: '50'))
    }

    agent {
        node {
            label 'clearAgent'
        }
    }

    stages {
        stage('Check TAGs') {
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
                                git tag | xargs git tag -d > /dev/null 2>&1
                                git fetch --tags
                            """
                        }
                    }
                }
            }
        }

        stage('Set build name') {
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

        stage('Build') {
            steps {
                    withCredentials([usernamePassword(credentialsId: "${TUZ_BUILD}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                        script {
                            if ( REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') || ( env.BRANCH_NAME.matches('PR-[0-9]+') && ( REAL_BRANCH.contains('feature/') || REAL_BRANCH.contains('bugfix/') ) ) ) {
                                echo "BUILD NOW"
                                sh './gradlew clean build -PciUsername=$ciUsername -PciPassword=$ciPassword'
                            } else {
                                echo "DO NOT BUILD THIS TIME"
                            }
                        }
                    }
            }
        }

        stage('Publish') {
            when {
                expression { REAL_BRANCH.contains('release/') || REAL_BRANCH.contains('develop') || ( env.BRANCH_NAME.matches('PR-[0-9]+') && ( REAL_BRANCH.contains('feature/') || REAL_BRANCH.contains('bugfix/') ) ) }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: "${TUZ_BUILD}", passwordVariable: 'ciPassword', usernameVariable: 'ciUsername')]) {
                    script {
                        ansiColor('xterm') {
                            echo "\033[32m############################## Публикация проекта под ТУЗ = ${TUZ_BUILD}\033[0m"
                        }
                        sh './gradlew publish -PciUsername=$ciUsername -PciPassword=$ciPassword --info'
                    }
                }
            }
        }

    }
}
