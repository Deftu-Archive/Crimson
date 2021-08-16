/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.matthewtgm.requisite.core.events;

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.keybinds.KeyBind;
import xyz.matthewtgm.simpleeventbus.Cancellable;
import xyz.matthewtgm.simpleeventbus.Event;

public class RequisiteEvent extends Event {
    public final IRequisite requisite;
    public RequisiteEvent(IRequisite requisite) {
        this.requisite = requisite;
    }
    public static class KeyEvent extends RequisiteEvent {
        public final KeyBind keyBind;
        public KeyEvent(IRequisite requisite, KeyBind keyBind) {
            super(requisite);
            this.keyBind = keyBind;
        }
        public static class KeyPressedEvent extends KeyEvent {
            public KeyPressedEvent(IRequisite requisite, KeyBind keyBind) {
                super(requisite, keyBind);
            }
        }
        public static class KeyReleasedEvent extends KeyEvent {
            public KeyReleasedEvent(IRequisite requisite, KeyBind keyBind) {
                super(requisite, keyBind);
            }
        }
        public static class KeyHeldEvent extends KeyEvent {
            public KeyHeldEvent(IRequisite requisite, KeyBind keyBind) {
                super(requisite, keyBind);
            }
        }
        @Cancellable
        public static class Pre extends KeyEvent {
            public Pre(IRequisite requisite, KeyBind keyBind) {
                super(requisite, keyBind);
            }
        }
        public static class Post extends KeyEvent {
            public Post(IRequisite requisite, KeyBind keyBind) {
                super(requisite, keyBind);
            }
        }
    }
    public static class Pre extends RequisiteEvent {
        public Pre(IRequisite requisite) {
            super(requisite);
        }
    }
    public static class Post extends RequisiteEvent {
        public Post(IRequisite requisite) {
            super(requisite);
        }
    }
}