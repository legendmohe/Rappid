/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.legendmohe.rappid.mvp;

/**
 * Created by xinyu.he on 16/03/28.
 */
public abstract class MvpBaseActivityPresenter<T extends MvpBaseView> extends MvpBasePresenter<T> {

    public MvpBaseActivityPresenter(T view) {
        super(view);
    }

    public void onActivityCreate() {

    }

    public void onActivityDestory() {

    }

    public void onActivityResume() {

    }

    public void onActivityPause() {

    }

    public void onActivityStart() {

    }

    public void onActivityStop() {

    }

    public void start() {

    }

    public void stop() {

    }
}